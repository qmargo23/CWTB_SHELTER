package pro.sky.CWTBshelter.handler;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import pro.sky.CWTBshelter.model.BotState;
import pro.sky.CWTBshelter.service.TelegramUserService;

@Component
public class StartHandler implements Handler {
    private final TelegramBot bot;
    private final TelegramUserService telegramUserService;
    private final String helloMessageText = "Привет! Я бот такой-то, умею делать то-то.";

    public StartHandler(TelegramBot bot, TelegramUserService telegramUserService) {
        this.bot = bot;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void makeTransition(Update update) {
        // Nothing...
    }

    @Override
    public BotState handle(Update update) {
        Long chatId = update.message().chat().id();
        sendHelloMessage(chatId);
        return BotState.WAIT_COMMAND;
    }

    public void sendHelloMessage(Long chatId) {
        SendMessage sendMessage = new SendMessage(chatId, helloMessageText);
        bot.execute(sendMessage);
    }
}
