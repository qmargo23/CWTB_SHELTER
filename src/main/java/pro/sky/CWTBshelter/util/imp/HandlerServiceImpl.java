package pro.sky.CWTBshelter.util.imp;

import com.pengrad.telegrambot.model.Update;
import org.springframework.stereotype.Service;
import pro.sky.CWTBshelter.model.BotState;
import pro.sky.CWTBshelter.model.ShelterUser;
import pro.sky.CWTBshelter.model.ShelterUserTelegram;
import pro.sky.CWTBshelter.repository.ShelterUserTelegramRepository;
import pro.sky.CWTBshelter.service.imp.ShelterUserTelegramServiceImp;
import pro.sky.CWTBshelter.util.HandlerService;
import pro.sky.CWTBshelter.util.MessageSender;

import java.util.Optional;

import static pro.sky.CWTBshelter.model.ShelterUserType.*;

@Service
public class HandlerServiceImpl implements HandlerService {
    private final String StartText = "Этот телеграмм-бот может отвечать на популярные вопросы людей о том, что нужно знать и уметь, чтобы забрать животное из приюта.";
    private final String helpText = "Наши волонтеры обязательно перезвонят! Для обратной связи, пожалуйста, оставьте свой контактный номер телефона в формате +7-9**-***-**-** . \n\nПри правильном вводе  Вашего номера телефона Вы увидите соответствующее сообщение!\n...чтобы скорректировать данные можете попробовать ввести номер телефона еще раз!\n ";
    private final String SAVENUMBERTEXT = "Ваш номер успешно сохранен, ожидайте звонка от волонтера.";
    private final String noReportText = "На данный момент у Вас нет питомца, вы не можете сдать отчет.";


    private final MessageSender messageSender;
    private final MenuServiceImp menuService;
    private final ShelterUserTelegramRepository userRepository;
    private final ShelterUserTelegramServiceImp userService;

    public HandlerServiceImpl(
            MessageSender messageSender,
            MenuServiceImp menuService,
            ShelterUserTelegramRepository userRepository,

            ShelterUserTelegramServiceImp userService) {
        this.messageSender = messageSender;
        this.menuService = menuService;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Override
    public void messageHandler(Update update) {
        Long chatId = update.message().chat().id();
        String userText = update.message().text();
        Optional<ShelterUserTelegram> user = userRepository.findSheltersUserTelegramByChatId(chatId);

        if (user.isPresent()) {
            Long id = user.get().getId();
            boolean phoneNumber = userService.setPhoneNumber(id, userText);
            if (phoneNumber) {
                messageSender.sendMessage(chatId, SAVENUMBERTEXT);
            }
        }

        if ("/start".equals(userText) || "Выбрать приют".equals(userText)) {
            if (user.isEmpty()) {
                ShelterUserTelegram regUser = new ShelterUserTelegram();
                regUser.setChatId(chatId);
                regUser.setBotState(BotState.START);
                regUser.setShelterUserType(JUST_LOOKING);

                regUser.setUserName(update.message().chat().firstName());
                regUser.setUserSurname(update.message().chat().lastName());

                userService.add(regUser);

                String greetingFirstMessageText = "Привет!!! " + update.message().chat().firstName() + "!\n" + StartText;
                menuService.setButtonKeyboard(update, greetingFirstMessageText);
            }
            menuService.getStartMenuShelter(chatId);
        }
        if ("/help".equals(userText) || "Помощь".equals(userText)) {
            messageSender.sendMessage(chatId, helpText);
        }
        if ("/adopt".equals(userText) || "Забрать питомца".equals(userText)) {
            menuService.getAdoptMenuShelter(chatId);
        }
        if ("/report".equals(userText) || "Сдать отчет".equals(userText)) {
            if (user.get().getAnimal() != null) {
                menuService.getReportMenuShelter(chatId);
            } else
                messageSender.sendMessage(chatId, noReportText);
        }
    }
}