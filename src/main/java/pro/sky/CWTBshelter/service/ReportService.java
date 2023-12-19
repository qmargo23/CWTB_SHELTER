package pro.sky.CWTBshelter.service;

import pro.sky.CWTBshelter.model.Report;

import java.util.List;

public interface ReportService {
    /**
     * Создает новый отчет.
     *
     * @param report отчет, который нужно создать
     * @return созданный отчет
     */
    Report createReport(Report report);

    /**
     * Получает отчет по его идентификатору.
     *
     * @param id Идентификатор отчета, который требуется получить.
     * @return Отчет с указанным идентификатором.
     */

    Report getReportById(long id);

    /**
     * @return список всех отчетов
     */
    List<Report> getAllReports();

    /**
     * Обновляет данный отчет.
     *
     * @param report Отчет, который необходимо обновить.
     * @return Обновленный отчет.
     */
    Report updateReport(Report report);

    /**
     * Удаляет отчет по его идентификатору.
     *
     * @param id — идентификатор удаляемого отчета.
     * @return true, если отчет был успешно удален, в противном случае — false
     */
    boolean deleteReportById(long id);
}