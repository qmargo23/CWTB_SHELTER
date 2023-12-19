package pro.sky.CWTBshelter.service;

import org.springframework.stereotype.Service;
import pro.sky.CWTBshelter.model.Report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class ReportService {
    private final Map<Long, Report> reportMap;

    public ReportService() {
        reportMap = new HashMap<>();
    }

    /**
     * Создает новый отчет и добавляет его в map отчетов.
     *
     * @param report отчет, который будет создан
     * @return созданный отчет
     */
    public Report createReport(Report report) {
        long id = generateId();
        report.setId(id);
        reportMap.put(id, report);
        return report;
    }

    /**
     * Извлекает отчет по его идентификатору.
     *
     * @param id идентификатор отчета, который нужно получить.
     * @return Отчет с указанным идентификатором или null, если он не существует.
     */
    public Report getReportById(long id) {
        return reportMap.get(id);
    }

    /**
     * Возвращает список всех отчетов.
     *
     * @return список всех отчетов
     */
    public List<Report> getAllReports() {
        return new ArrayList<>(reportMap.values());
    }
    /**
     * Обновляет данный отчет в Map отчетов.
     *
     * @param  report отчет для обновления
     * @return  обновленный отчет
     */
    public Report updateReport(Report report) {
        long id = report.getId();
        if (reportMap.containsKey(id)) {
            reportMap.put(id, report);
            return report;
        } else {
            throw new IllegalArgumentException("Отчет с идентификатором " + id + " не существует");
        }
    }
    /**
     * Удаляет отчет с указанным идентификатором из отчетов.
     *
     * @param id идентификатор удаляемого отчета
     * @throws IllegalArgumentException, если отчет с указанным идентификатором не существует
     */
    public boolean deleteReportById(long id) {
        if (reportMap.containsKey(id)) {
            reportMap.remove(id);
            return true;
        } else {
            throw new IllegalArgumentException("Отчет с идентификатором " + id + " не существует");
        }
    }
    /**
     * Генерирует уникальный идентификатор.
     *
     * @return  сгенерированный идентификатор.
     */
    private int generateId() {
        return 0;
    }
}