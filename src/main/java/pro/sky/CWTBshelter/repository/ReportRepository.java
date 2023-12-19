package pro.sky.CWTBshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.CWTBshelter.model.Report;

import java.util.Optional;
/**
 * ReportRepository - репозиторий связывающий БД и приложение
 */
@Repository
public interface ReportRepository extends JpaRepository<Report,Long> {
    Optional<Report> getReportById(Long id);
}
