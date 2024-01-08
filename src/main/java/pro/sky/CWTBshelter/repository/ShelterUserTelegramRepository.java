package pro.sky.CWTBshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.CWTBshelter.model.ShelterUser;
import pro.sky.CWTBshelter.model.ShelterUserTelegram;

import java.util.Optional;

@Repository
public interface ShelterUserTelegramRepository extends JpaRepository<ShelterUserTelegram, Long> {
    Optional<ShelterUserTelegram> findSheltersUserTelegramByChatId(Long chatId);
}
