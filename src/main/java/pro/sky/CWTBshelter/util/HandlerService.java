package pro.sky.CWTBshelter.util;

import com.pengrad.telegrambot.model.Update;

public interface HandlerService {
    /**
     * Обработка сообщений от пользователя(согласно выбранному пункту меню)
     * @param update обновление
     */
    void messageHandler(Update update);
}
