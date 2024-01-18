package pro.sky.CWTBshelter.controller;

// ReportControllerTest.java

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pro.sky.CWTBshelter.dto.ReportDTO;
import pro.sky.CWTBshelter.dto.mapper.ReportDTOMapper;
import pro.sky.CWTBshelter.model.Report;
import pro.sky.CWTBshelter.service.ReportService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(ReportController.class)

class ReportControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private ReportService reportService;
    @SpyBean
    private ReportDTOMapper reportDTOMapper;
    @Autowired
    private ObjectMapper objectMapper;
    static Report reportTest = new Report(1L,"Фото",LocalDate.of(2023,1,1),
            "Текст под под фото");
    static ReportDTO.Request.Create requestTest = new ReportDTO.Request.Create("Фото",LocalDate.of(2023,1,1),
            "Текст под под фото");
    List<Report> list = new ArrayList<>(List.of(new Report(1L,"Photo",LocalDate.of(2022,1,1),
            "Text under photo"),new Report(2L,"Фото",LocalDate.of(2000,1,1),
            "Фотоның астындағы мәтін")));

    @Test
    @DisplayName("Добавление отчета")
    void createReportTest() throws Exception {
        when(reportService.createReport(requestTest)).thenReturn(reportTest);
        mvc.perform(MockMvcRequestBuilders.post("/report")
                        .content(objectMapper.writeValueAsString(reportTest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.photo").value("Фото"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.localDate").value("2023-01-01"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.reportTextUnderPhoto").value("Текст под под фото"))
                .andExpect(status().isOk());

        verify(reportService, only()).createReport(requestTest);
    }

    @Test
    @DisplayName("Вывод отчета по id")
    void getReportByIdTest() throws Exception {
        when(reportService.getReportById(anyLong())).thenReturn(reportTest);
        mvc.perform(MockMvcRequestBuilders.get("/report/{id}", reportTest.getId()))
                .andExpect(status().isOk());
        verify(reportService, only()).getReportById(reportTest.getId());
    }

    @Test
    @DisplayName("Редактирование отчета")
    void updateReportTest() throws Exception {
        when(reportService.updateReport(1L, requestTest)).thenReturn(reportTest);

        mvc.perform(MockMvcRequestBuilders.put("/report/1")
                        .content(objectMapper.writeValueAsString(reportTest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.photo").value("Фото"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.localDate").value("2023-01-01"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.reportTextUnderPhoto").value("Текст под под фото"))

                .andExpect(status().isOk());

        verify(reportService, only()).updateReport(1L, requestTest);
    }

    @Test
    @DisplayName("Удаление отчета")
    void deleteReportByIdTest() throws Exception {
        when(reportService.deleteReportById(anyLong())).thenReturn(true);
        mvc.perform(delete("/report/{id}", anyLong()))
                .andExpect(status().isOk());
    }
    @Test
    @DisplayName("Получить все отчеты")
    void getAllReportsTest()throws Exception{
        when(reportService.getAllReports()).thenReturn(list);

        mvc.perform(MockMvcRequestBuilders.get("/report")
                        .content(objectMapper.writeValueAsString(list))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$",
                        Matchers.hasSize(Matchers.greaterThan(0))))
                .andExpect(status().isOk());
        verify(reportService, only()).getAllReports();
    }
}