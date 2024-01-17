package pro.sky.CWTBshelter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pro.sky.CWTBshelter.model.ReportTelegram;
import pro.sky.CWTBshelter.model.ShelterUserTelegram;
import pro.sky.CWTBshelter.service.ReportTelegramService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReportTelegramController.class)
class ReportTelegramControllerTest {
    @Autowired
    MockMvc mvc;
    @MockBean
    private ReportTelegramService reportTelegramService;
    @Autowired
    private ObjectMapper objectMapper;
    static ShelterUserTelegram shelterUserTelegramTest = new ShelterUserTelegram();
    static ReportTelegram reportTelegramTest = new ReportTelegram(1L,"Фото", LocalDate.of(2023,1,1),
            "Текст под под фото",shelterUserTelegramTest);
    static ReportTelegram reportTelegramTestAdd = new ReportTelegram(1L,"Фото", LocalDate.of(2023,1,1),
            "Текст под под фото",shelterUserTelegramTest);
    List<ReportTelegram> list = new ArrayList<>(List.of(new ReportTelegram(1L,"Photo",LocalDate.of(2022,1,1),"Text under photo",shelterUserTelegramTest),
            new ReportTelegram(2L,"Фото",LocalDate.of(2020,1,1),"Фотосуреттің астындағы мәтін",shelterUserTelegramTest)));
    @Test
    void addTelegramReport() throws Exception {
        when(reportTelegramService.createTelegramReport(reportTelegramTest)).thenReturn(reportTelegramTestAdd);
        mvc.perform(MockMvcRequestBuilders.post("/reportsTelegram")
                        .content(objectMapper.writeValueAsString(reportTelegramTest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.photo").value("Фото"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.localDate").value("2023-01-01"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.reportTextUnderPhoto").value("Текст под под фото"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sheltersUser").value(reportTelegramTest.getSheltersUser()))
                ;

        verify(reportTelegramService, only()).createTelegramReport(reportTelegramTestAdd);
    }

    @Test
    void getAllTelegramReports() throws Exception{
        when(reportTelegramService.getAllTelegramReports()).thenReturn(list);

        mvc.perform(MockMvcRequestBuilders.get("/reportsTelegram")
                        .content(objectMapper.writeValueAsString(list))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$",
                        Matchers.hasSize(Matchers.greaterThan(0))))
                .andExpect(status().isOk());
        verify(reportTelegramService, only()).getAllTelegramReports();
    }

    @Test
    void getReportTelegramById() throws Exception {
            when(reportTelegramService.findTelegramReportById(anyLong())).thenReturn(reportTelegramTest);
            mvc.perform(MockMvcRequestBuilders.get("/reportsTelegram/{id}", reportTelegramTest.getId()))
                    .andExpect(status().isOk());
            verify(reportTelegramService, only()).findTelegramReportById(reportTelegramTest.getId());
    }

    @Test
    void editTelegramReport() throws Exception {
        when(reportTelegramService.editTelegramReport(reportTelegramTest)).thenReturn(reportTelegramTestAdd);

        mvc.perform(MockMvcRequestBuilders.put("/reportsTelegram")
                        .content(objectMapper.writeValueAsString(reportTelegramTest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.photo").value("Фото"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.localDate").value("2023-01-01"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.reportTextUnderPhoto").value("Текст под под фото"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sheltersUser").value(reportTelegramTest.getSheltersUser()))
                .andExpect(status().isOk());

        verify(reportTelegramService, only()).editTelegramReport(reportTelegramTest);
    }

    @Test
    void deleteTelegramReport() throws Exception {
        when(reportTelegramService.deleteTelegramReportById(anyLong())).thenReturn(true);
        mvc.perform(delete("/reportsTelegram/{id}", anyLong()))
                .andExpect(status().isOk());
    }
}