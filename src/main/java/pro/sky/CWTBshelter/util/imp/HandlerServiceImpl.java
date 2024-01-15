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
        //ищем user в БД
        Optional<ShelterUserTelegram> user = userRepository.findSheltersUserTelegramByChatId(chatId);

        //блок проверки на ввод номера телефона!
        if (user.isPresent()) {
            Long id = user.get().getId();
            // setPhoneNumber - проверка
            boolean phoneNumber = userService.setPhoneNumber(id, userText);
            if (phoneNumber) {
                messageSender.sendMessage(chatId, SAVENUMBERTEXT);
            }
        }

        if ("/start".equals(userText) || "Выбрать приют".equals(userText)) {
// перенесла выше           Optional<ShelterUser> user = userRepository.findSheltersUserByChatId(chatId);
            //регистрируем и приветствуем нового пользователя приюта
            if (user.isEmpty()) {
                ShelterUserTelegram regUser = new ShelterUserTelegram();
                regUser.setChatId(chatId);
                regUser.setBotState(BotState.START);
                regUser.setShelterUserType(JUST_LOOKING);
                //можно пропустить этот блок/ возможно заполнение волонтером
                regUser.setUserName(update.message().chat().firstName());
                regUser.setUserSurname(update.message().chat().lastName());
                //добавляем нового пользователя в БД shelter_user_telegram
                userService.add(regUser);
                //приветствуем нового пользователя//+//установка "меню-кнопки" при  регистрации
                String greetingFirstMessageText = "Привет!!! " + update.message().chat().firstName() + "!\n" + StartText;
                menuService.setButtonKeyboard(update, greetingFirstMessageText);
            }
            //вызов старт-меню /выбор приюта  с двумя кнопками
            menuService.getStartMenuShelter(chatId);
        }
        if ("/help".equals(userText) || "Помощь".equals(userText)) {
            messageSender.sendMessage(chatId, helpText);
        }
        if ("/adopt".equals(userText) || "Забрать питомца".equals(userText)) {
            //на данном этапе в разработке 
            menuService.getAdoptMenuShelter(chatId);
        }
        if ("/report".equals(userText) || "Сдать отчет".equals(userText)) {
            //проверка: если у user есть животное
            if (user.get().getAnimal() != null) {
                menuService.getReportMenuShelter(chatId);
            } else
                messageSender.sendMessage(chatId, noReportText);
        }
    }
}