package pro.sky.CWTBshelter.service;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.CWTBshelter.exception.ReportNotFoundException;
import pro.sky.CWTBshelter.model.Report;
import pro.sky.CWTBshelter.model.ReportTelegram;
import pro.sky.CWTBshelter.model.ShelterUser;
import pro.sky.CWTBshelter.model.ShelterUserTelegram;
import pro.sky.CWTBshelter.repository.ReportTelegramRepository;
import pro.sky.CWTBshelter.repository.ShelterUserTelegramRepository;
import pro.sky.CWTBshelter.service.imp.ReportTelegramServiceImpl;
import pro.sky.CWTBshelter.util.MessageSender;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReportTelegramServiceTest {
    @Mock
    private ReportTelegramRepository reportTelegramRepository;
    @Mock
    private ShelterUserTelegramRepository shelterUserTelegramRepository;
    @Mock
    private MessageSender messageSender;
    @InjectMocks
    private ReportTelegramServiceImpl reportTelegramService;

    static ShelterUserTelegram shelterUserTelegramTest = new ShelterUserTelegram();
    static ReportTelegram reportTelegramTest = new ReportTelegram(1L,"Фото",LocalDate.of(2023,1,1),
            "Текст под под фото",shelterUserTelegramTest);
    static Update updateTest = new Update();


    @BeforeEach
    public void beforeEach(){

        lenient().when(reportTelegramRepository.findById(1L)).thenReturn(Optional.of(reportTelegramTest));

    }

    @Test
    void createTelegramReport() {
        ReportTelegram expected =new ReportTelegram();
        expected.setId(1L);
        expected.setPhoto("Фото");
        expected.setLocalDate(LocalDate.of(2023,1,1));
        expected.setReportTextUnderPhoto("Текст по фото");
        expected.setSheltersUser(shelterUserTelegramTest);

        when(reportTelegramRepository.save(reportTelegramTest)).thenReturn(new ReportTelegram(1L,"Фото",LocalDate.of(2023,1,1),
                "Текст под под фото",shelterUserTelegramTest));

        ReportTelegram actual = reportTelegramService.createTelegramReport(new ReportTelegram(1L,"Фото",LocalDate.of(2023,1,1),
                "Текст под под фото",shelterUserTelegramTest));

        assertEquals(expected, actual);
    }

    @Test
    void editTelegramReport() {
        ReportTelegram expected = new ReportTelegram(1L,"Фото",LocalDate.of(2023,1,1),
                "Текст под под фото",shelterUserTelegramTest);
        when(reportTelegramRepository.save(reportTelegramTest)).thenReturn(new ReportTelegram(1L,"Фото",LocalDate.of(2023,1,1),
                "Текст под под фото",shelterUserTelegramTest));
        ReportTelegram actual = reportTelegramService.editTelegramReport(reportTelegramTest);
        assertEquals(actual,expected);
    }

    @Test
    void findTelegramReportById() {
        ReportTelegram expected = new ReportTelegram(1L,"Фото",LocalDate.of(2023,1,1),
                "Текст под под фото",shelterUserTelegramTest);
        ReportTelegram actual = reportTelegramService.findTelegramReportById(1L);
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void getAllTelegramReports() {
        when(reportTelegramRepository.findAll()).thenReturn(List.of(new ReportTelegram(1L,"Фото",LocalDate.of(2023,1,1),
            "Текст под под фото",shelterUserTelegramTest)));
        List<ReportTelegram> actual = reportTelegramService.getAllTelegramReports();
        List<ReportTelegram> expected = new ArrayList<>();
        expected.add(new ReportTelegram(1L,"Фото",LocalDate.of(2023,1,1),
                "Текст под под фото",shelterUserTelegramTest));
        Assertions.assertEquals(actual,expected);
    }

    @Test
    void deleteTelegramReportById() {
        Assertions.assertThrows(ReportNotFoundException.class,()->reportTelegramService.deleteTelegramReportById(2L));
    }


}