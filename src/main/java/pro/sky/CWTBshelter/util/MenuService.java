package pro.sky.CWTBshelter.util;

import com.pengrad.telegrambot.request.SendMessage;

/**
 * Отвечает за кнопки
 */
public interface MenuService {
    /**
     * Выдает стартовое меню выбора приюта
     *
     * @param chatId чат пользователя
     * @return возвращает пользователю сообщение с двумя кнопками выбора приюта
     */
    SendMessage getStartMenuShelter(Long chatId);

    /**
     * вызывается меню  "cat" приюта
     *
     * @param chatId чат пользователя
     * @return возвращает пользователю сообщение о CAT приюте
     */
    SendMessage getCatMenu(Long chatId);

    /**
     * вызывается меню  "dog" приюта
     *
     * @param chatId чат пользователя
     * @return возвращает пользователю сообщение о DOG приюте
     */
    SendMessage getDogMenu(Long chatId);

    /**
     * выдает меню для усыновления питомца из приюта
     *
     * @param chatId чат пользователя
     * @return пока в разработке
     */
    SendMessage getAdoptMenuShelter(Long chatId);

    /**
     * выдает меню для сдачи отчета пользователем
     *
     * @param chatId чат пользователя
     * @return пока в разработке
     */
    SendMessage getReportMenuShelter(Long chatId);

    /**
     * вызывается меню  с доступом к информации о усыновлении кота или кошки
     *
     * @param chatId чат пользователя
     * @return возвращает пользователю меню  с информацией об усыновлении кота или кошки
     */
    SendMessage getCatAdoptMenu(Long chatId);

    /**
     * вызывается меню  с доступом к информации о усыновлении собаки
     *
     * @param chatId чат пользователя
     * @return возвращает пользователю меню  с информацией об усыновлении собаки
     */
    SendMessage getDogAdoptMenu(Long chatId);

    /**
     * вызывается меню  с доступом к информации о приюте
     * @param chatId чат пользователя
     * @return возвращает пользователю меню  с информацией о приюте
     */
    SendMessage getShelterInfoMenu(Long chatId);

}
