package pro.sky.CWTBshelter.service;

import pro.sky.CWTBshelter.dto.ShelterUserDTO;
import pro.sky.CWTBshelter.model.ShelterUser;

import java.util.List;

public interface ShelterUserService {
    /**
     * Возвращает пользователя приюта по его id
     *
     * @param id уникальный идентификатор пользователя приюта
     * @return найденный пользователь приюта
     */
    ShelterUser findShelterUserById(Long id);

    /**
     * Возвращает всех пользователей приюта
     *
     * @return список пользователей приюта
     */
    List<ShelterUser> getAllShelterUsers();

    /**
     * Добавляет пользователя приюта в базу данных
     *
     * @param request пользователь, которого нужно добавить
     * @return добавленный пользователь
     */
    ShelterUser createShelterUser(ShelterUserDTO.Request.Create request);

    /**
     * Сохраняет обновленную информацию о пользователе приюта в базу данных
     *
     * @param id      идентификатор пользователя
     * @param request обновленная информация о пользователе
     * @return измененный пользователь
     */
    ShelterUser updateShelterUser(Long id, ShelterUserDTO.Request.Create request);

    /**
     * Удаляет пользователя приюта по id из базы данных
     *
     * @param id идентификатор пользователя, которого нужно удалить
     */
    void removeById(Long id);
}
