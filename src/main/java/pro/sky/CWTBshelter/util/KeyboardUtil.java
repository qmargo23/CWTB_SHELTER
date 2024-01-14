package pro.sky.CWTBshelter.util;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import org.springframework.stereotype.Service;
import pro.sky.CWTBshelter.init.CallbackDataRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Создает кнопки с соответствующими названиями и значениями<br>
 * @see CallbackDataRequest
 */
@Service
public class KeyboardUtil {
    // strings может содержать только те константы, которые есть в enum CallbackDataRequest

    /**
     * Устанавливает кнопки в условную клавиатуру
     * @param strings название кнопок
     * @return объект, представляющий собой встроенную клавиатуру {@link InlineKeyboardMarkup  }
     */
    public InlineKeyboardMarkup setKeyboard(CallbackDataRequest... strings) {
        List<InlineKeyboardButton> keyboardButtons = getKeyboardButtons(strings);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        for (InlineKeyboardButton keyboardButton : keyboardButtons) {
            inlineKeyboardMarkup.addRow(keyboardButton);
        }
        return inlineKeyboardMarkup;
    }

    /**
     * Выдает кнопки по местам в условной клавиатуре
     * @param strings название кнопок
     * @return
     */

    private List<InlineKeyboardButton> getKeyboardButtons(CallbackDataRequest... strings) {
        List<InlineKeyboardButton> inlineKeyboardButtons = new ArrayList<>();
        for (CallbackDataRequest string : strings) {
            inlineKeyboardButtons.add(button(string.getText(), string.getCallbackData()));
        }
        return inlineKeyboardButtons;
    }

    private InlineKeyboardButton button(String text, String callbackData) {
        InlineKeyboardButton button = new InlineKeyboardButton(text);
        button.callbackData(callbackData);
        return button;
    }
}