package pro.sky.CWTBshelter.service.imp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.CWTBshelter.dto.ReportDTO;
import pro.sky.CWTBshelter.dto.mapper.ReportDTOMapper;
import pro.sky.CWTBshelter.exception.ReportNotFoundException;
import pro.sky.CWTBshelter.model.*;
import pro.sky.CWTBshelter.repository.ReportRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class ReportServiceImplTest {

    @Mock
    private ReportRepository reportRepository;

    @Spy
    private ReportDTOMapper reportDTOMapper;

    @InjectMocks
    private ReportServiceImpl reportService;
    @BeforeEach
    public void beforeEach(){
        Report reportTest = new Report(1L,"Фото",LocalDate.of(2023,1,1),
                "Текст под под фото");
        lenient().when(reportRepository.findById(1L)).thenReturn(Optional.of(reportTest));

    }



    @Test
    void createReportTest() {
        Report reportTest = new Report(null,"Фото",LocalDate.of(2023,1,1),
                "Текст под под фото");
        Report expected =new Report(1L,"Фото",LocalDate.of(2023,1,1),
                "Текст под под фото");
        when(reportRepository.save(reportTest)).thenReturn(new Report(1L,"Фото",LocalDate.of(2023,1,1),
                "Текст под под фото"));

        Report actual = reportService.createReport(new ReportDTO.Request.Create("Фото",LocalDate.of(2023,1,1),
                "Текст под под фото"));

        assertEquals(expected, actual);
    }

    @Test
    void getReportByIdTest() {
        Report expected = new Report(1L,"Фото",LocalDate.of(2023,1,1),
                "Текст под под фото");
        Report actual = reportService.getReportById(1L);
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void updateReportTest() {
        Report expected = new Report(1L,"Фото",LocalDate.of(2023,1,1),
                "Текст под под фото");
        Report reportTest = new Report(1L,"Фото",LocalDate.of(2023,1,1),
                "Текст под под фото");
        ReportDTO.Request.Create request = new ReportDTO.Request.Create("Фото",LocalDate.of(2023,1,1),
                "Текст под под фото");
        when(reportRepository.save(reportTest)).thenReturn(new Report(1L,"Фото",LocalDate.of(2023,1,1),
                "Текст под под фото"));

        Report actual = reportService.updateReport(1L,request);

        Assertions.assertEquals(expected,actual);
    }

    @Test
    void deleteReportByIdTest() {
        Assertions.assertThrows(ReportNotFoundException.class,()->reportService.deleteReportById(2L));
    }

    @Test
    void getAllReportsTest() {
        when(reportRepository.findAll()).thenReturn(List.of(new Report(1L,"Фото",LocalDate.of(2023,1,1),
                "Текст под под фото")));
        List<Report> actual = reportRepository.findAll();
        List<Report> expected = new ArrayList<>();
        expected.add(new Report(1L,"Фото",LocalDate.of(2023,1,1),
                "Текст под под фото"));
        Assertions.assertEquals(actual,expected);

    }
}