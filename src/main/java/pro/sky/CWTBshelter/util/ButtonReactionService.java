package pro.sky.CWTBshelter.util;

import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.request.SendMessage;

public interface ButtonReactionService {
    SendMessage buttonReaction(CallbackQuery callbackQuery);
}
