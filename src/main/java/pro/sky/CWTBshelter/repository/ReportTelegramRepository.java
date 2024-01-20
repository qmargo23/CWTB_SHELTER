package pro.sky.CWTBshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.CWTBshelter.model.ReportTelegram;

import java.util.List;

/**
 * ReportTelegramRepository - репозиторий связывающий БД и приложение
 */
@Repository
public interface ReportTelegramRepository extends JpaRepository<ReportTelegram,Long> {
    List<ReportTelegram> findBySheltersUserId(Long id);
}
