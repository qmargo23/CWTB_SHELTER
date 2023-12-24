package pro.sky.CWTBshelter.service;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.CWTBshelter.model.ShelterInfo;

import java.util.List;

public interface ShelterInfoService {
    /**
     * Создание нового питомника и сохранение его в базу данных <br>
     * @see JpaRepository#save(Object)
     * @param shelterInfo объект класса {@link ShelterInfo}, не может быть {@code null}
     * @return созданный объект класса {@link ShelterInfo}
     */
    ShelterInfo createShelterInfo(ShelterInfo shelterInfo);

    /**
     * Поиск питомника в базе данных по его {@code id} <br>
     * @see JpaRepository#findById(Object)
     * @param id идентификатор искомого питомника, не может быть {@code null}
     * @throws pro.sky.CWTBshelter.exception.ShelterInfoNotFoundException если питомник с указанным {@code id} не было найдено в базе данных
     * @return найденный объект класса {@link ShelterInfo}
     */
    ShelterInfo findShelterInfoById(Long id);

    /**
     * Редактирование питомника и сохранение его в базу данных <br>
     * @see  JpaRepository#save(Object)
     * @param shelterInfo объект класса {@link ShelterInfo}, не может быть {@code null}
     * @return отредактированный объект класса {@link ShelterInfo}
     * @throws pro.sky.CWTBshelter.exception.ShelterInfoNotFoundException если питомник не был найден в базе данных
     */
    ShelterInfo editShelterInfo(ShelterInfo shelterInfo);

    /**
     * Удаление питомника из базы данных по его {@code id} <br>
     * @see JpaRepository#deleteById(Object)
     * @param id идентификатор питомника, которого нужно удалить из базы данных, не может быть {@code null}
     * @throws pro.sky.CWTBshelter.exception.ShelterInfoNotFoundException если питомник с указанным {@code id} не был найден в базе данных
     * @return {@code true} если объект был найден в базе данных, в противном случае {@link pro.sky.CWTBshelter.exception.ShelterInfoNotFoundException}
     */
    boolean deleteShelterInfoById(Long id);

    /**
     * Вывод списка всех питомников из базы данных<br>
     * @see JpaRepository#findAll()
     * @return список объектов класса {@link ShelterInfo}
     */
    List<ShelterInfo> getAllShelters();

    // Методы Коли
    /**
     * Извлекает контактную информацию для автомобильного пропуска.
     *
     * @return Контактная информация автомобильного пропуска.
     */
    String getContactForCarPass();
    /**
     * Возвращает документы.
     *
     * @return документы в виде строки.
     */
    String getDocuments();
    /**
     * Получение рекомендаций по транспортировке.
     *
     * @return Консультации по транспортировке.
     */
    String getTransportationAdvice();

}
