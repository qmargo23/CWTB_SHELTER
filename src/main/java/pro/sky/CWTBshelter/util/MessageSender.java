package pro.sky.CWTBshelter.util;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;

/**
 * Отвечает за отправку сообщений
 * @see SendMessage
 */
@Service
public class MessageSender {
    private final TelegramBot telegramBot;

    public MessageSender(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    /**
     * Отправляет сообщения
     * @param chatId  идентификатор чата
     * @param message  сообщение
     * @return сообщение
     */
    public SendMessage sendMessage(Long chatId, String message) {
        SendMessage sendMessage = new SendMessage(chatId, message);
        telegramBot.execute(sendMessage);
        return sendMessage;
    }
}