package pro.sky.CWTBshelter.util;

import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.request.SendMessage;

public interface ButtonReactionService {
    /**
     * Выдает реакции на кнопки выбранные пользователем
     * @param callbackQuery-объект {@link CallbackQuery}
     * @return Сообщение пользователю
     */
    SendMessage buttonReaction(CallbackQuery callbackQuery);
}
