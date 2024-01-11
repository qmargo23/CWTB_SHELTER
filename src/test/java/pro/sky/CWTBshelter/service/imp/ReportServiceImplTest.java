package pro.sky.CWTBshelter.service.imp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pro.sky.CWTBshelter.dto.ReportDTO;
import pro.sky.CWTBshelter.dto.mapper.ReportDTOMapper;
import pro.sky.CWTBshelter.model.Report;
import pro.sky.CWTBshelter.repository.ReportRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class ReportServiceImplTest {

    @Mock
    private ReportRepository reportRepository;

    @Mock
    private ReportDTOMapper reportDTOMapper;

    @InjectMocks
    private ReportServiceImpl reportService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createReportTest() {
        when(reportRepository.save(any(Report.class))).thenReturn(someReport);
        when(reportDTOMapper.toReportDTO(any(Report.class))).thenReturn(someReportDTO);

        ReportDTO createdReport = reportService.createReport(someReportRequest);

        assertEquals(someReportDTO, createdReport);
    }

    @Test
    void getReportByIdTest() {
        when(reportRepository.findById(anyLong())).thenReturn(Optional.of(someReport));
        when(reportDTOMapper.toReportDTO(any(Report.class))).thenReturn(someReportDTO);

        ReportDTO foundReport = reportService.getReportById(someReportId);

        assertEquals(someReportDTO, foundReport);
    }

    @Test
    void updateReportTest() {
        when(reportRepository.findById(anyLong())).thenReturn(Optional.of(someReport));
        when(reportRepository.save(any(Report.class))).thenReturn(updatedReport);

        Report updatedReport = reportService.updateReport(someReportId, someReportRequest);

        assertEquals(updatedReport, someUpdatedReport);
    }

    @Test
    void deleteReportByIdTest() {
        when(reportRepository.findById(anyLong())).thenReturn(Optional.of(someReport));

        boolean isDeleted = reportService.deleteReportById(someReportId);

        assertTrue(isDeleted);
    }

    @Test
    void getAllReportsTest() {
        when(reportRepository.findAll()).thenReturn(someReports);
        when(reportDTOMapper.toReportDTO(any(Report.class))).thenReturn(someReportDTO);

        List<ReportDTO> allReports = reportService.getAllReports();

        assertEquals(someReportDTOList, allReports);
    }
}
