package pro.sky.CWTBshelter.util.imp;

import com.pengrad.telegrambot.model.Update;
import org.springframework.stereotype.Service;
import pro.sky.CWTBshelter.util.HandlerService;
import pro.sky.CWTBshelter.util.MessageSender;


@Service
public class HandlerServiceImpl implements HandlerService {
    private final String StartText = "Этот телеграмм-бот может отвечать на популярные вопросы людей о том, что нужно знать и уметь, чтобы забрать животное из приюта.";
    private final String helpText = "Наши волонтеры обязательно перезвонят! Для обратной связи, пожалуйста, оставьте свой контактный номер телефона в формате +7-9**-***-**-**";

    private final MessageSender messageSender;
    private final MenuServiceImp menuService;

    public HandlerServiceImpl(
            MessageSender messageSender,
            MenuServiceImp menuService) {
        this.messageSender = messageSender;
        this.menuService = menuService;
    }

    @Override
    public void messageHandler(Update update) {
        Long chatId = update.message().chat().id();
        String userText = update.message().text();

        if ("/start".equals(userText) || "Выбрать приют".equals(userText)) {
            // проверка на наличие user в БД userRepository

            //регистрируем и приветствуем нового пользователя приюта

            //приветствуем нового пользователя//+//установка "меню-кнопки" при  регистрации
            String greetingFirstMessageText = "Привет!!! " + update.message().chat().firstName() + "!\n" + StartText;
            menuService.setButtonKeyboard(update, greetingFirstMessageText);

            //вызов старт-меню /выбор приюта  с двумя кнопками
            menuService.getStartMenuShelter(chatId);
        }
        if ("/help".equals(userText) || "Помощь".equals(userText)) {
            messageSender.sendMessage(chatId, helpText);
        }
    }
}