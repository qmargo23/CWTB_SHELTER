package pro.sky.CWTBshelter.service.imp;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetFileResponse;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pro.sky.CWTBshelter.exception.ReportNotFoundException;
import pro.sky.CWTBshelter.model.ReportTelegram;
import pro.sky.CWTBshelter.model.ShelterUserTelegram;
import pro.sky.CWTBshelter.repository.ReportTelegramRepository;
import pro.sky.CWTBshelter.repository.ShelterUserTelegramRepository;
import pro.sky.CWTBshelter.service.ReportTelegramService;
import pro.sky.CWTBshelter.util.MessageSender;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

@Service
public class ReportTelegramServiceImpl implements ReportTelegramService {
    private final String postReportTextToday = "Вы уже отправляли ОТЧЕТ за сегодня!";
    private final String postReportTextToday_NoAnimal = "Бот не может принять Ваши данные, поскольку у Вас нет питомца взятого из нашего приюта.";
    private final String postAttentionText = "Для отчета необходимо отсылать  фото и описание. В следующий раз, пожалуйста, сделайте фото и описание вашего подопечного!";
    private final String postReportText = "Отчет отправлен, пожалуйста, не забывайте отправлять отчеты о вашем питомце ежедневно.";
    private final String postReportTodayText = "Сегодня надо прислать отчет о жизни вашего питомца, пожалуйста,\n не забывайте отправлять отчеты о вашем питомце ежедневно.";
    private final String postReportTwoDaysText = "Вы не присылали отчет уже 2 дня...";


    private final TelegramBot telegramBot;
    private final ShelterUserTelegramRepository userRepository;
    private final MessageSender messageSender;
    private final ReportTelegramRepository reportTelegramRepository;

    public ReportTelegramServiceImpl(TelegramBot telegramBot, ShelterUserTelegramRepository userRepository, ReportTelegramRepository reportTelegramRepository, MessageSender messageSender) {
        this.telegramBot = telegramBot;
        this.userRepository = userRepository;
        this.messageSender = messageSender;
        this.reportTelegramRepository = reportTelegramRepository;
    }

    @Override
    public SendMessage postReport(Long chatId, Update update) {

        Optional<ShelterUserTelegram> user = userRepository.findSheltersUserTelegramByChatId(chatId);

        if (user.isPresent()) {
            if (user.get().getAnimal() == null) {
                return messageSender.sendMessage(chatId, postReportTextToday_NoAnimal);
            }

            List<ReportTelegram> bySheltersUserId = reportTelegramRepository.findBySheltersUserId(user.get().getId());
            if (!bySheltersUserId.isEmpty()) {
                for (ReportTelegram rez : bySheltersUserId) {
                    if (rez.getLocalDate().isEqual(LocalDate.now())) {
                        return messageSender.sendMessage(chatId, postReportTextToday);
                    }
                }
            }
        }
        String caption = update.message().caption();//описание под картинкой
        ReportTelegram newReport = new ReportTelegram();
        user.ifPresent(newReport::setSheltersUser);

        newReport.setReportTextUnderPhoto(caption);
        newReport.setLocalDate(LocalDate.now());

        PhotoSize photoSize = update.message().photo()[update.message().photo().length - 1];
        GetFileResponse getFileResponse = telegramBot.execute(new GetFile(photoSize.fileId()));
        if (getFileResponse.isOk()) {
            String extension = StringUtils.getFilenameExtension(getFileResponse.file().filePath());
            try {
                byte[] image = telegramBot.getFileContent(getFileResponse.file());
                Path write = Files.write(Paths.get(UUID.randomUUID() + "." + extension), image);
                newReport.setPhoto(write.toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (Objects.isNull(newReport.getPhoto()) || Objects.isNull(newReport.getReportTextUnderPhoto())) {
            messageSender.sendMessage(chatId, postAttentionText);
        }
        reportTelegramRepository.save(newReport);
        return messageSender.sendMessage(chatId, postReportText);
    }

    @Override
    public ReportTelegram createTelegramReport(ReportTelegram reportTelegram) {
        reportTelegramRepository.save(reportTelegram);
        return reportTelegram;
    }

    @Override
    public ReportTelegram editTelegramReport(ReportTelegram reportTelegram) {
        if (reportTelegramRepository.findById(reportTelegram.getId()).isPresent()) {
            reportTelegramRepository.save(reportTelegram);
            return reportTelegram;
        } else {
            throw new ReportNotFoundException();
        }
    }

    @Override
    public ReportTelegram findTelegramReportById(Long id) {
        return reportTelegramRepository.findById(id).orElseThrow(ReportNotFoundException::new);
    }

    @Override
    public List<ReportTelegram> getAllTelegramReports() {
        return reportTelegramRepository.findAll();
    }

    @Override
    public boolean deleteTelegramReportById(Long id) {
        if (reportTelegramRepository.findById(id).isPresent()) {
            reportTelegramRepository.deleteById(id);
            return true;
        } else {
            throw new ReportNotFoundException();
        }
    }

    @Scheduled(cron = "0 00 10 * * *")//сообщение-напоминание отсылается всем пользователям каждый день в 10
    public void reportReminder() {
        List<ShelterUserTelegram> users = userRepository.findSheltersUserTelegramByAdoptDateIsNotNull();
        if (!users.isEmpty()) {
            for (ShelterUserTelegram user : users) {
                Long chatId = user.getChatId();
                messageSender.sendMessage(chatId, postReportTodayText);
            }
        }
    }
    @Scheduled(cron = "0 00 15 * * *")
    public void reportReminderTwoDays() {

        List<ShelterUserTelegram> users = userRepository.findSheltersUserTelegramByAdoptDateIsNotNull();
        if (!users.isEmpty()) {
            for (ShelterUserTelegram user : users) {
                Long chatId = user.getChatId();
                //Сортируем, ищем последний отчет!
                Optional<ReportTelegram> report = reportTelegramRepository.findBySheltersUserId(
                                user.getId())
                        .stream()
                        .sorted(Comparator.comparing(ReportTelegram::getLocalDate))
                        .reduce((x, y) -> y);

                if (report.isPresent()) {
                    //прибавили к дате последнего отчета 2 дня
                    LocalDate reportDate = report.get().getLocalDate().plusDays(2);
                    LocalDate currentDate = LocalDate.now();
                    //сравниваем с текущей датой, если больше, значит отчет старый
                    if (currentDate.isAfter(reportDate)) {
                        messageSender.sendMessage(chatId, postReportTwoDaysText);
                    }
                }
            }
        }
    }


}
