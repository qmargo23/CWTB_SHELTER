package pro.sky.CWTBshelter.service;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.CWTBshelter.dto.AnimalDTO;
import pro.sky.CWTBshelter.exception.AnimalNotFoundException;
import pro.sky.CWTBshelter.model.Animal;

import java.util.List;

public interface AnimalService {
    /**
     * Создание нового питомца и сохранение его в базу данных <br>
     *
     * @param request объект, не может быть {@code null}
     * @return созданный объект класса {@link Animal}
     * @see JpaRepository#save(Object)
     */
    Animal createAnimal(AnimalDTO.Request.Create request);

    /**
     * Поиск питомца в базе данных по его {@code id} <br>
     *
     * @param id идентификатор искомого животного, не может быть {@code null}
     * @return найденный объект класса {@link Animal}
     * @throws pro.sky.CWTBshelter.exception.AnimalNotFoundException если животное с указанным {@code id} не было найдено в базе данных
     * @see JpaRepository#findById(Object)
     */
    Animal findAnimalById(Long id);

    /**
     * Редактирование питомца и сохранение его в базу данных <br>
     *
     * @param request объект, не может быть {@code null}
     * @return отредактированный объект класса {@link Animal}
     * @throws AnimalNotFoundException если животное не было найдено в базе данных
     * @see JpaRepository#save(Object)
     */
    Animal editAnimal(Long id, AnimalDTO.Request.Create request);

    /**
     * Удаление питомца из базы данных по его {@code id} <br>
     *
     * @param id идентификатор животного, которого нужно удалить из базы данных, не может быть {@code null}
     * @return {@code true} если объект был найден в базе данных, в противном случае {@link AnimalNotFoundException}
     * @throws AnimalNotFoundException если животное с указанным {@code id} не было найдено в базе данных
     * @see JpaRepository#deleteById(Object)
     */
    boolean deleteAnimalById(Long id);

    /**
     * Вывод списка всех питомцев приюта из базы данных<br>
     *
     * @return список объектов класса {@link Animal}
     * @see JpaRepository#findAll()
     */
    List<Animal> getAllAnimals();
}
