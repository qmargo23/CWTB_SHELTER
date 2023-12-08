package pro.sky.CWTBshelter.handler;


import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;

@Component
public class WaitCommandHandler implements Handler {
    private final TelegramBot bot;
    private final String helpMessage = "Commands:\n/help - get help;\n/info - get information about project.";
    private final String infoMessage = "This is an online shelter :D";
    private final String unknownCommandMessage = "Unknown command :/ Write /help to get help.";

    public WaitCommandHandler(TelegramBot bot) {
        this.bot = bot;
    }

    @Override
    public void handle(Update update) {
        Long chatId = update.message().chat().id();
        String messageText = update.message().text().strip();
        switch (messageText) {
            case "/help" -> sendHelp(chatId);
            case "/info" -> sendInfo(chatId);
            default -> sendUnknownCommand(chatId);
        }
    }

    public void sendHelp(Long chatId) {
        SendMessage request = new SendMessage(chatId, helpMessage);
        bot.execute(request);
    }

    public void sendInfo(Long chatId) {
        SendMessage request = new SendMessage(chatId, infoMessage);
        bot.execute(request);
    }

    public void sendUnknownCommand(Long chatId) {
        SendMessage request = new SendMessage(chatId, unknownCommandMessage);
        bot.execute(request);
    }
}
