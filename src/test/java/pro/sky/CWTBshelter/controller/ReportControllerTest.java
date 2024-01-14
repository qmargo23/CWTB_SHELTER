package pro.sky.CWTBshelter.controller;

// ReportControllerTest.java

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pro.sky.CWTBshelter.service.ReportService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ReportControllerTest {

    @Mock
    private ReportService reportService;

    @InjectMocks
    private ReportController reportController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(reportController).build();
    }

    @Test
    void createReportTest() throws Exception {
        when(reportService.createReport(any())).thenReturn(someReportDTO);

        mockMvc.perform(post("/reports")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(someReportRequestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(someReportDTO.getId()))
                .andExpect(jsonPath("$.name").value(someReportDTO.getName()));

        verify(reportService, times(1)).createReport(any());
    }

    @Test
    void getReportByIdTest() throws Exception {
        when(reportService.getReportById(anyLong())).thenReturn(someReportDTO);

        mockMvc.perform(get("/reports/{id}", someReportId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(someReportDTO.getId()))
                .andExpect(jsonPath("$.name").value(someReportDTO.getName()));

        verify(reportService, times(1)).getReportById(anyLong());
    }

    @Test
    void updateReportTest() throws Exception {
        when(reportService.updateReport(anyLong(), any())).thenReturn(someUpdatedReportDTO);

        mockMvc.perform(put("/reports/{id}", someReportId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(someReportRequestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(someUpdatedReportDTO.getId()))
                .andExpect(jsonPath("$.name").value(someUpdatedReportDTO.getName()));

        verify(reportService, times(1)).updateReport(anyLong(), any());
    }

    @Test
    void deleteReportByIdTest() throws Exception {
        when(reportService.deleteReportById(anyLong())).thenReturn(true);

        mockMvc.perform(delete("/reports/{id}", someReportId))
                .andExpect(status().isOk());

        verify(reportService, times(1)).deleteReportById(anyLong());
    }
}