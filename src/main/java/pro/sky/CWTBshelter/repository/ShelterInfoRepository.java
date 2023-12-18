package pro.sky.CWTBshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.CWTBshelter.model.ShelterInfo;
/**
 * <b>ShelterInfo</b> <i>- репозиторий связывающий БД и приложение</i>
 */
@Repository
public interface ShelterInfoRepository extends JpaRepository<ShelterInfo,Long> {
}
