package pro.sky.CWTBshelter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pro.sky.CWTBshelter.model.BotState;
import pro.sky.CWTBshelter.model.ShelterUserTelegram;
import pro.sky.CWTBshelter.model.ShelterUserType;
import pro.sky.CWTBshelter.service.ShelterUserTelegramService;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ShelterUserTelegramController.class)
public class ShelterUserTelegramControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private ShelterUserTelegramService shelterUserTelegramService;
    @Autowired
    private ObjectMapper objectMapper;

    private final ShelterUserTelegram shelterUserTelegram1 = new ShelterUserTelegram(
            1L, 1L, BotState.START, "username1", "usersurname1",
            "+7-999-999-99-99", LocalDate.now(), ShelterUserType.JUST_LOOKING, null
    );

    private final ShelterUserTelegram shelterUserTelegramToAdd1 = new ShelterUserTelegram(
            null, 1L, BotState.START, "username1", "usersurname1",
            "+7-999-999-99-99", LocalDate.now(), ShelterUserType.JUST_LOOKING, null
    );

    private final ShelterUserTelegram shelterUserTelegram2 = new ShelterUserTelegram(
            2L, 2L, BotState.START, "username2", "usersurname2",
            "+7-988-888-88-88", LocalDate.now(), ShelterUserType.JUST_LOOKING, null
    );

    private final List<ShelterUserTelegram> shelterUserTelegrams = List.of(shelterUserTelegram1, shelterUserTelegram2);

    @Test
    public void getShelterUserTelegramById() throws Exception {
        when(shelterUserTelegramService.findById(shelterUserTelegram1.getId())).thenReturn(shelterUserTelegram1);

        mvc.perform(MockMvcRequestBuilders.get("/user-telegram/{id}", shelterUserTelegram1.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(shelterUserTelegram1.getId()))
                .andExpect(jsonPath("$.chatId").value(shelterUserTelegram1.getChatId()))
                .andExpect(jsonPath("$.userName").value(shelterUserTelegram1.getUserName()))
                .andExpect(jsonPath("$.userSurname").value(shelterUserTelegram1.getUserSurname()))
                .andExpect(jsonPath("$.userPhoneNumber").value(shelterUserTelegram1.getUserPhoneNumber()))
                .andExpect(jsonPath("$.adoptDate").value(shelterUserTelegram1.getAdoptDate().toString()))
                .andExpect(jsonPath("$.shelterUserType").value(shelterUserTelegram1.getShelterUserType().toString()))
                .andExpect(jsonPath("$.animal").value(shelterUserTelegram1.getAnimal()));

        verify(shelterUserTelegramService, only()).findById(shelterUserTelegram1.getId());
    }

    @Test
    public void getAllShelterUsersTelegram() throws Exception {
        when(shelterUserTelegramService.findAll()).thenReturn(shelterUserTelegrams);

        mvc.perform(MockMvcRequestBuilders.get("/user-telegram"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(shelterUserTelegrams.size())));

        verify(shelterUserTelegramService, only()).findAll();
    }

    @Test
    public void createShelterUserTelegram() throws Exception {
        when(shelterUserTelegramService.add(shelterUserTelegramToAdd1)).thenReturn(shelterUserTelegram1);

        mvc.perform(MockMvcRequestBuilders.post("/user-telegram")
                        .content(objectMapper.writeValueAsString(shelterUserTelegramToAdd1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(shelterUserTelegram1.getId()))
                .andExpect(jsonPath("$.chatId").value(shelterUserTelegram1.getChatId()))
                .andExpect(jsonPath("$.userName").value(shelterUserTelegram1.getUserName()))
                .andExpect(jsonPath("$.userSurname").value(shelterUserTelegram1.getUserSurname()))
                .andExpect(jsonPath("$.userPhoneNumber").value(shelterUserTelegram1.getUserPhoneNumber()))
                .andExpect(jsonPath("$.adoptDate").value(shelterUserTelegram1.getAdoptDate().toString()))
                .andExpect(jsonPath("$.shelterUserType").value(shelterUserTelegram1.getShelterUserType().toString()))
                .andExpect(jsonPath("$.animal").value(shelterUserTelegram1.getAnimal()));

        verify(shelterUserTelegramService, only()).add(shelterUserTelegramToAdd1);
    }

    @Test
    public void updateShelterUserTelegram() throws Exception {
        when(shelterUserTelegramService.update(shelterUserTelegramToAdd1)).thenReturn(shelterUserTelegram1);

        mvc.perform(MockMvcRequestBuilders.put("/user-telegram/{id}", shelterUserTelegram1.getId())
                        .content(objectMapper.writeValueAsString(shelterUserTelegramToAdd1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(shelterUserTelegram1.getId()))
                .andExpect(jsonPath("$.chatId").value(shelterUserTelegramToAdd1.getChatId()))
                .andExpect(jsonPath("$.userName").value(shelterUserTelegramToAdd1.getUserName()))
                .andExpect(jsonPath("$.userSurname").value(shelterUserTelegramToAdd1.getUserSurname()))
                .andExpect(jsonPath("$.userPhoneNumber").value(shelterUserTelegramToAdd1.getUserPhoneNumber()))
                .andExpect(jsonPath("$.adoptDate").value(shelterUserTelegramToAdd1.getAdoptDate().toString()))
                .andExpect(jsonPath("$.shelterUserType").value(shelterUserTelegramToAdd1.getShelterUserType().toString()))
                .andExpect(jsonPath("$.animal").value(shelterUserTelegramToAdd1.getAnimal()));

        verify(shelterUserTelegramService, only()).update(shelterUserTelegramToAdd1);
    }

    @Test
    public void deleteShelterUserTelegram() throws Exception {
        when(shelterUserTelegramService.removeById(shelterUserTelegram1.getId())).thenReturn(true);

        mvc.perform(MockMvcRequestBuilders.delete("/user-telegram/{id}", shelterUserTelegram1.getId()))
                .andExpect(status().isOk());

        verify(shelterUserTelegramService, only()).removeById(shelterUserTelegram1.getId());
    }

    @Test
    public void setPhoneNumberTelegram() throws Exception {
        String phoneNumber = "+7-933-333-33-33";
        when(shelterUserTelegramService.setPhoneNumber(shelterUserTelegram1.getId(), phoneNumber)).thenReturn(true);

        mvc.perform(MockMvcRequestBuilders.put("/user-telegram/{id}/phone-number-from-telegram?phoneNumber={phone}", shelterUserTelegram1.getId(), phoneNumber))
                .andExpect(status().isOk());
        verify(shelterUserTelegramService, only()).setPhoneNumber(shelterUserTelegram1.getId(), phoneNumber);
    }
}
