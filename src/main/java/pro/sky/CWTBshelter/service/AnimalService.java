package pro.sky.CWTBshelter.service;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.CWTBshelter.exception.AnimalNotFoundException;
import pro.sky.CWTBshelter.model.Animal;

import java.util.List;

public interface AnimalService {
    /**
     * Создание нового питомца и сохранение его в базу данных <br>
     * @see JpaRepository#save(Object)
     * @param animal объект класса {@link Animal}, не может быть {@code null}
     * @return созданный объект класса {@link Animal}
     */
    Animal createAnimal(Animal animal);

    /**
     * Поиск питомца в базе данных по его {@code id} <br>
     * @see JpaRepository#findById(Object)
     * @param id идентификатор искомого животного, не может быть {@code null}
     * @throws pro.sky.CWTBshelter.exception.AnimalNotFoundException если животное с указанным {@code id} не было найдено в базе данных
     * @return найденный объект класса {@link Animal}
     */
    Animal findAnimalById(Long id);

    /**
     * Редактирование питомца и сохранение его в базу данных <br>
     * @see  JpaRepository#save(Object)
     * @param animal объект класса {@link Animal}, не может быть {@code null}
     * @return отредактированный объект класса {@link Animal}
     * @throws AnimalNotFoundException если животное не было найдено в базе данных
     */
    Animal editAnimal(Animal animal);

    /**
     * Удаление питомца из базы данных по его {@code id} <br>
     * @see JpaRepository#deleteById(Object)
     * @param id идентификатор животного, которого нужно удалить из базы данных, не может быть {@code null}
     * @throws AnimalNotFoundException если животное с указанным {@code id} не было найдено в базе данных
     * @return {@code true} если объект был найден в базе данных, в противном случае {@link AnimalNotFoundException}
     */
    boolean deleteAnimalById(Long id);

    /**
     * Вывод списка всех питомцев приюта из базы данных<br>
     * @see JpaRepository#findAll()
     * @return список объектов класса {@link Animal}
     */
    List<Animal> getAllAnimals();
}
