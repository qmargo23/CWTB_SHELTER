package pro.sky.CWTBshelter.handler;


import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import pro.sky.CWTBshelter.model.BotState;

@Component
public class WaitCommandHandler implements Handler {
    private final TelegramBot bot;
    private final String transitionMessageText = "Жду указаний.";
    private final String infoButtonText = "📄 Информация";
    private final String helpButtonText = "❔ Нужна помощь";
    private final String helpMessageText = "Какая-то справочная информация...";
    private final String infoMessageText = "Информация о боте...";
    private final String unknownMessageText = "Я вас не понимаю 😢";

    public WaitCommandHandler(TelegramBot bot) {
        this.bot = bot;
    }

    @Override
    public void makeTransition(Update update) {
        // keyboard
        Keyboard keyboard = new ReplyKeyboardMarkup(infoButtonText, helpButtonText)
                .resizeKeyboard(true)
                .selective(true);
        // send message
        SendMessage request = new SendMessage(update.message().chat().id(), transitionMessageText)
                .replyMarkup(keyboard);
        bot.execute(request);
    }

    @Override
    public BotState handle(Update update) {
        Long chatId = update.message().chat().id();
        String messageText = update.message().text().strip();
        switch (messageText) {
            case helpButtonText -> sendHelp(chatId);
            case infoButtonText -> sendInfo(chatId);
            default -> sendUnknownCommand(chatId);
        }
        return BotState.WAIT_COMMAND;
    }

    public void sendHelp(Long chatId) {
        SendMessage request = new SendMessage(chatId, helpMessageText);
        bot.execute(request);
    }

    public void sendInfo(Long chatId) {
        SendMessage request = new SendMessage(chatId, infoMessageText);
        bot.execute(request);
    }

    public void sendUnknownCommand(Long chatId) {
        SendMessage request = new SendMessage(chatId, unknownMessageText);
        bot.execute(request);
    }
}
