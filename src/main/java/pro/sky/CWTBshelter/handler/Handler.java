package pro.sky.CWTBshelter.handler;

import com.pengrad.telegrambot.model.Update;

public interface Handler {
    /**
     * Handle update.
     * @param update update from Telegram
     */
    void handle(Update update);
}
