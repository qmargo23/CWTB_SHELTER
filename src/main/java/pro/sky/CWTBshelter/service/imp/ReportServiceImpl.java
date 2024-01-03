package pro.sky.CWTBshelter.service.imp;

import org.springframework.stereotype.Service;
import pro.sky.CWTBshelter.dto.ReportDTO;
import pro.sky.CWTBshelter.dto.mapper.ReportDTOMapper;
import pro.sky.CWTBshelter.exception.ReportNotFoundException;
import pro.sky.CWTBshelter.model.Report;
import pro.sky.CWTBshelter.repository.ReportRepository;
import pro.sky.CWTBshelter.service.ReportService;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;
    private final ReportDTOMapper reportDTOMapper;

    public ReportServiceImpl(ReportRepository reportRepository, ReportDTOMapper reportDTOMapper) {
        this.reportRepository = reportRepository;
        this.reportDTOMapper = reportDTOMapper;
    }

    /**
     * Создает отчет и сохраняет его в репозитории.
     *
     * @param request Отчет, который необходимо создать и сохранить.
     * @return Созданный отчет.
     */
    @Override
    public Report createReport(ReportDTO.Request.Create request) {
        Report report = reportDTOMapper.toReport(request);
        return reportRepository.save(report);
    }

    /**
     * Получает отчет по его идентификатору.
     *
     * @param id Идентификатор отчета, который требуется получить.
     * @return Отчет с указанным идентификатором.
     * @throws ReportNotFoundException, если отчет с указанным идентификатором не найден.
     */
    @Override
    public Report getReportById(long id) {
        return reportRepository.findById(id).orElseThrow(ReportNotFoundException::new);
    }

    /**
     * Извлекает все отчеты из хранилища.
     *
     * @return Список объектов отчета.
     */
    @Override
    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    /**
     * Обновляет отчет в репозитории.
     *
     * @param request Отчет, который необходимо обновить.
     * @return Обновленный отчет.
     * @throws ReportNotFoundException, если отчет с указанным идентификатором не найден.
     */
    @Override
    public Report updateReport(Long id, ReportDTO.Request.Create request) {
        Report report = reportRepository.findById(id).orElseThrow(ReportNotFoundException::new);
        report.setPhoto(request.getPhoto());
        report.setLocalDate(request.getLocalDate());
        report.setReportTextUnderPhoto(report.getReportTextUnderPhoto());
        return reportRepository.save(report);
    }

    /**
     * Удаляет отчет по его идентификатору.
     *
     * @param id Идентификатор удаляемого отчета.
     * @return True, если отчет успешно удален, в противном случае — false.
     * @throws ReportNotFoundException Если отчет с указанным идентификатором не найден.
     */
    @Override
    public boolean deleteReportById(long id) {
        if (reportRepository.findById(id).isPresent()) {
            reportRepository.deleteById(id);
            return true;
        } else {
            throw new ReportNotFoundException();
        }
    }
}
