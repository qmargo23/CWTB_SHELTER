package pro.sky.CWTBshelter.util;

import com.pengrad.telegrambot.request.SendMessage;

/**
 * Отвечает за кнопки
 */
public interface MenuService {
    /**
     * Выдает стартовое меню выбора приюта
     * @param chatId чат пользователя
     * @return возвращает пользователю сообщение с двумя кнопками выбора приюта
     */
    SendMessage getStartMenuShelter(Long chatId);
   }
