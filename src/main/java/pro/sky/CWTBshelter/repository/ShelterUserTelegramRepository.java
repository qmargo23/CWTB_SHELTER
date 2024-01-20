package pro.sky.CWTBshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.CWTBshelter.model.ShelterUserTelegram;

import java.util.List;
import java.util.Optional;

/**
 * <b>ShelterUserTelegramRepository</b> <i>-репозиторий связывающий БД и приложение</i>
 */
@Repository
public interface ShelterUserTelegramRepository extends JpaRepository<ShelterUserTelegram, Long> {
    Optional<ShelterUserTelegram> findSheltersUserTelegramByChatId(Long chatId);//findSheltersUserTelegram
    List<ShelterUserTelegram> findSheltersUserTelegramByAdoptDateIsNotNull();//findShelterUserByAdoptDateIsNotNull()

}
