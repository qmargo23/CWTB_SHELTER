package pro.sky.CWTBshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.CWTBshelter.model.Avatar;

import java.util.Optional;
/**
 * <b>AvatarRepository</b> <i>- репозиторий связывающий БД и приложение</i>
 */
@Repository
public interface AvatarRepository extends JpaRepository<Avatar,Long> {
    Optional<Avatar> findByShelterInfoId(Long id);
}
