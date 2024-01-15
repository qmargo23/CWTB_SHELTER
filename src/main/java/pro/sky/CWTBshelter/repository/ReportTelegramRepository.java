package pro.sky.CWTBshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pro.sky.CWTBshelter.model.ReportTelegram;

import java.time.LocalDate;
import java.util.Optional;

/**
 * ReportTelegramRepository - репозиторий связывающий БД и приложение
 */
@Repository
public interface ReportTelegramRepository extends JpaRepository<ReportTelegram,Long> {
    @Query(value = "SELECT * \n" +
            "FROM report_telegram r \n" +
            "WHERE local_date = CURRENT_DATE\n" +
            "ORDER BY local_date ASC\n" +
            "LIMIT 1;",
            nativeQuery = true)
    Optional<ReportTelegram> findReportByToday(LocalDate date);
}
