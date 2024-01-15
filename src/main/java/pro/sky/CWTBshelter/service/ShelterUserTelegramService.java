package pro.sky.CWTBshelter.service;

import pro.sky.CWTBshelter.model.ShelterUserTelegram;

import java.util.List;

public interface ShelterUserTelegramService {
    /**
     * Возвращает Telegram-пользователя приюта по его id
     *
     * @param id уникальный идентификатор пользователя приюта
     * @return найденный пользователь приюта
     */
    ShelterUserTelegram findById(Long id);

    /**
     * Возвращает всех Telegram-пользователей приюта
     *
     * @return список пользователей приюта
     */
    List<ShelterUserTelegram> findAll();

    /**
     * Добавляет пользователя приюта в базу данных
     *
     * @param shelterUserTelegram Telegram-пользователь, которого нужно добавить
     * @return добавленный пользователь
     */
    ShelterUserTelegram add(ShelterUserTelegram shelterUserTelegram);

    /**
     * Сохраняет обновленную информацию о пользователе приюта в базу данных
     *
     * @param shelterUserTelegram обновленная информация о пользователе
     * @return измененный пользователь
     */
    ShelterUserTelegram update(ShelterUserTelegram shelterUserTelegram);

    /**
     * Удаляет пользователя приюта по id из базы данных
     *
     * @param id идентификатор пользователя, которого нужно удалить
     */
    boolean removeById(Long id);
    /**
     * Устанавливает номер телефона для заданного идентификатора.
     *
     * @param id Идентификатор пользователя.
     * @param phoneNumber Номер телефона, который необходимо установить.
     */
    boolean setPhoneNumber(Long id,String phoneNumber);
}


