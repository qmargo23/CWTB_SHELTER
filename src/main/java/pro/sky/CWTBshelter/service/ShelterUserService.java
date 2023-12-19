package pro.sky.CWTBshelter.service;

import pro.sky.CWTBshelter.model.ShelterUser;

public interface ShelterUserService {
    /**
     * Возвращает пользователя приюта по его id
     *
     * @param id уникальный идентификатор пользователя приюта
     * @return найденный пользователь приюта
     */
    ShelterUser findById(Long id);

    /**
     * Добавляет пользователя приюта в базу данных
     *
     * @param shelterUser пользователь, которого нужно добавить
     * @return добавленный пользователь
     */
    ShelterUser add(ShelterUser shelterUser);

    /**
     * Сохраняет обновленную информацию о пользователе приюта в базу данных
     *
     * @param shelterUser обновленная информация о пользователе
     * @return измененный пользователь
     */
    ShelterUser update(ShelterUser shelterUser);

    /**
     * Удаляет пользователя приюта по id из базы данных
     *
     * @param id идентификатор пользователя, которого нужно удалить
     */
    void removeById(Long id);
}
