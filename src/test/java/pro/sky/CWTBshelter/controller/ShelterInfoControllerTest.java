package pro.sky.CWTBshelter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pro.sky.CWTBshelter.model.ShelterInfo;
import pro.sky.CWTBshelter.service.ShelterInfoService;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ShelterInfoController.class)
class ShelterInfoControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private ShelterInfoService shelterInfoService;
    @Autowired
    private ObjectMapper objectMapper;

    static ShelterInfo shelterInfoTest = new ShelterInfo(1L, "Про приют", "Адрес",
            "Номер телефона", "Безопасность на территории",
            "Знакомство", "Документы",
            "Рекомендации к транспортировке",
            "Для маленьких", "Для больших",
            "С особенностями", "Рекомендации",
            "Кинолог", "Отказы");
    List<ShelterInfo> list = new ArrayList<>(List.of(new ShelterInfo(1L, "About shelter", "Address",
            "Phone number", "Safety on territory",
            "Meet", "Documents",
            "Transportation advice",
            "For small", "For adult",
            "With disability", "Advice",
            "Handler", "Refuse"), new ShelterInfo(2L, "Баспана туралы", "Мекенжай",
            "Телефон нөмірі", "Сайттағы қауіпсіздік",
            "Танысу", "Құжаттама",
            "Тасымалдау бойынша ұсыныстар",
            "Кішкентайлар үшін", "Үлкендер үшін",
            "Ерекшеліктерімен", "Ұсыныстар",
            "Ит күтуші", "Сәтсіздіктер")));

    @Test
    @DisplayName("Добавление приюта")
    void addShelterInfo() throws Exception {
        when(shelterInfoService.createShelterInfo(shelterInfoTest)).thenReturn(shelterInfoTest);

        mvc.perform(MockMvcRequestBuilders.post("/shelter")
                        .content(objectMapper.writeValueAsString(shelterInfoTest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.aboutShelter").value("Про приют"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addressSchedule").value("Адрес"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.contactForCarPass").value("Номер телефона"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.safetyOnTerritory").value("Безопасность на территории"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstMeetRecommendation").value("Знакомство"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.documents").value("Документы"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.transportationAdvice").value("Рекомендации к транспортировке"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.houseRulesForSmallAnimal").value("Для маленьких"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.houseRulesForAdultAnimal").value("Для больших"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rulesForAnimalWithDisability").value("С особенностями"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cynologistAdvice").value("Рекомендации"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cynologists").value("Кинолог"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.refuseReasons").value("Отказы"))
                .andExpect(status().isOk());

        verify(shelterInfoService, only()).createShelterInfo(shelterInfoTest);


    }

    @Test
    @DisplayName("Вывод питомника по его id")
    void getShelterInfoById() throws Exception {
        when(shelterInfoService.findShelterInfoById(anyLong())).thenReturn(shelterInfoTest);
        mvc.perform(MockMvcRequestBuilders.get("/shelter/{id}", shelterInfoTest.getId()))
                .andExpect(status().isOk());
        verify(shelterInfoService, only()).findShelterInfoById(shelterInfoTest.getId());
    }

    @Test
    @DisplayName("Редактирование приюта")
    void editShelterInfo() throws Exception {
        when(shelterInfoService.editShelterInfo(shelterInfoTest)).thenReturn(shelterInfoTest);

        mvc.perform(MockMvcRequestBuilders.put("/shelter")
                        .content(objectMapper.writeValueAsString(shelterInfoTest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.aboutShelter").value("Про приют"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addressSchedule").value("Адрес"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.contactForCarPass").value("Номер телефона"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.safetyOnTerritory").value("Безопасность на территории"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstMeetRecommendation").value("Знакомство"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.documents").value("Документы"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.transportationAdvice").value("Рекомендации к транспортировке"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.houseRulesForSmallAnimal").value("Для маленьких"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.houseRulesForAdultAnimal").value("Для больших"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rulesForAnimalWithDisability").value("С особенностями"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cynologistAdvice").value("Рекомендации"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cynologists").value("Кинолог"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.refuseReasons").value("Отказы"))
                .andExpect(status().isOk());

        verify(shelterInfoService, only()).editShelterInfo(shelterInfoTest);

    }

    @Test
    @DisplayName("Удаление приюта")
    void deleteShelterInfo() throws Exception {
        when(shelterInfoService.deleteShelterInfoById(anyLong())).thenReturn(true);
        mvc.perform(delete("/shelter/{id}", anyLong()))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Получить все приюты")
    void getAllShelters() throws Exception {
        when(shelterInfoService.getAllShelters()).thenReturn(list);

        mvc.perform(MockMvcRequestBuilders.get("/shelter")
                        .content(objectMapper.writeValueAsString(list))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$",
                        Matchers.hasSize(Matchers.greaterThan(0))))
                .andExpect(status().isOk());
        verify(shelterInfoService, only()).getAllShelters();
    }

    @Test
    @DisplayName("Вывод рекомендации при первом знакомстве с питомцем")
    void getFirstMeetRecommendation() throws Exception {
        when(shelterInfoService.getFirstMeetRecommendation()).thenReturn("Знакомство");
        mvc.perform(MockMvcRequestBuilders.get("/shelter/first-meet",
                        shelterInfoTest.getFirstMeetRecommendation()))
                .andExpect(content().string(containsString("Знакомство")))
                .andExpect(status().isOk());
        verify(shelterInfoService, only()).getFirstMeetRecommendation();
    }

    @Test
    @DisplayName("Вывод причин по которым могут отказать в том что бы забрать питомца")
    void getRefuseReasons() throws Exception {
        when(shelterInfoService.getRefuseReasons()).thenReturn("Отказы");
        mvc.perform(MockMvcRequestBuilders.get("/shelter/refuse-reasons",
                        shelterInfoTest.getRefuseReasons()))
                .andExpect(content().string(containsString("Отказы")))
                .andExpect(status().isOk());
        verify(shelterInfoService, only()).getRefuseReasons();
    }

    @Test
    @DisplayName("Вывод правил безопасности на территории приюта")
    void getSafetyOnTerritory() throws Exception {
        when(shelterInfoService.getSafetyOnTerritory()).thenReturn("Безопасность на территории");
        mvc.perform(MockMvcRequestBuilders.get("/shelter/safety-on-territory",
                        shelterInfoTest.getSafetyOnTerritory()))
                .andExpect(content().string(containsString("Безопасность на территории")))
                .andExpect(status().isOk());
        verify(shelterInfoService, only()).getSafetyOnTerritory();
    }

    @Test
    @DisplayName("Вывод информации о приюте")
    void getAboutShelter() throws Exception {
        when(shelterInfoService.getAboutShelter()).thenReturn("Про приют");
        mvc.perform(MockMvcRequestBuilders.get("/shelter/aboutShelter",
                        shelterInfoTest.getAboutShelter()))
                .andExpect(content().string(containsString("Про приют")))
                .andExpect(status().isOk());
        verify(shelterInfoService, only()).getAboutShelter();
    }

    @Test
    @DisplayName("Вывод адреса и часах работы приюта")
    void getAddressShelter() throws Exception {
        when(shelterInfoService.getAddressShelter()).thenReturn("Адрес");
        mvc.perform(MockMvcRequestBuilders.get("/shelter/getAddressSchedule",
                        shelterInfoTest.getAddressSchedule()))
                .andExpect(content().string(containsString("Адрес")))
                .andExpect(status().isOk());
        verify(shelterInfoService, only()).getAddressShelter();
    }
}