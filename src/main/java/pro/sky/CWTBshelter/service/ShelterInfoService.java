package pro.sky.CWTBshelter.service;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.CWTBshelter.dto.ShelterInfoDTO;
import pro.sky.CWTBshelter.model.ShelterInfo;

import java.util.List;

public interface ShelterInfoService {
    /**
     * Создание нового питомника и сохранение его в базу данных <br>
     *
     * @param request объект, не может быть {@code null}
     * @return созданный объект класса {@link ShelterInfo}
     * @see JpaRepository#save(Object)
     */
    ShelterInfo createShelterInfo(ShelterInfoDTO.Request.Create request);

    /**
     * Поиск питомника в базе данных по его {@code id} <br>
     *
     * @param id идентификатор искомого питомника, не может быть {@code null}
     * @return найденный объект класса {@link ShelterInfo}
     * @throws pro.sky.CWTBshelter.exception.ShelterInfoNotFoundException если питомник с указанным {@code id} не было найдено в базе данных
     * @see JpaRepository#findById(Object)
     */
    ShelterInfo findShelterInfoById(Long id);

    /**
     * Редактирование питомника и сохранение его в базу данных <br>
     *
     * @param request объект, не может быть {@code null}
     * @return отредактированный объект класса {@link ShelterInfo}
     * @throws pro.sky.CWTBshelter.exception.ShelterInfoNotFoundException если питомник не был найден в базе данных
     * @see JpaRepository#save(Object)
     */
    ShelterInfo editShelterInfo(Long id, ShelterInfoDTO.Request.Create request);

    /**
     * Удаление питомника из базы данных по его {@code id} <br>
     *
     * @param id идентификатор питомника, которого нужно удалить из базы данных, не может быть {@code null}
     * @return {@code true} если объект был найден в базе данных, в противном случае {@link pro.sky.CWTBshelter.exception.ShelterInfoNotFoundException}
     * @throws pro.sky.CWTBshelter.exception.ShelterInfoNotFoundException если питомник с указанным {@code id} не был найден в базе данных
     * @see JpaRepository#deleteById(Object)
     */
    boolean deleteShelterInfoById(Long id);

    /**
     * Вывод списка всех питомников из базы данных<br>
     *
     * @return список объектов класса {@link ShelterInfo}
     * @see JpaRepository#findAll()
     */
    List<ShelterInfo> getAllShelters();
}
