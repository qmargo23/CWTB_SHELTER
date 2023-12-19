package pro.sky.CWTBshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.CWTBshelter.model.Animal;

/**
 * <b>AnimalRepository</b> <i>- репозиторий связывающий БД и приложение</i>
 */
@Repository
public interface AnimalRepository extends JpaRepository<Animal,Long> {

}
