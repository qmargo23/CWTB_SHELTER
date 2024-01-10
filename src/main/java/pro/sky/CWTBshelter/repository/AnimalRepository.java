package pro.sky.CWTBshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pro.sky.CWTBshelter.model.Animal;

import java.util.List;

/**
 * <b>AnimalRepository</b> <i>- репозиторий связывающий БД и приложение</i>
 */
@Repository
public interface AnimalRepository extends JpaRepository<Animal,Long> {
    @Query(value = "SELECT * FROM animal where in_shelter = true AND type_animal = 'dog' ", nativeQuery = true)
public List<Animal> dogInShelter();
    @Query(value = "SELECT * FROM animal where in_shelter = true AND type_animal = 'cat' ", nativeQuery = true)
    public List<Animal> catInShelter();



}
