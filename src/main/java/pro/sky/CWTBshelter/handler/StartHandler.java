package pro.sky.CWTBshelter.handler;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import pro.sky.CWTBshelter.model.BotState;
import pro.sky.CWTBshelter.model.TelegramUser;
import pro.sky.CWTBshelter.service.TelegramUserService;

@Component
public class StartHandler implements Handler {
    private final TelegramBot bot;
    private final TelegramUserService telegramUserService;
    private final String messageText = "Hi!";

    public StartHandler(TelegramBot bot, TelegramUserService telegramUserService) {
        this.bot = bot;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void handle(Update update) {
        Long chatId = update.message().chat().id();
        sayHi(chatId);
        updateBotState(chatId);
    }

    public void sayHi(Long chatId) {
        SendMessage sendMessage = new SendMessage(chatId, messageText);
        bot.execute(sendMessage);
    }

    public void updateBotState(Long chatId) {
        TelegramUser telegramUser = telegramUserService.findByChatId(chatId);
        if (telegramUser == null) {
            telegramUser = telegramUserService.add(chatId, BotState.WAIT_COMMAND);
        }
        telegramUser.setBotState(BotState.WAIT_COMMAND);
        telegramUserService.update(telegramUser);
    }
}
