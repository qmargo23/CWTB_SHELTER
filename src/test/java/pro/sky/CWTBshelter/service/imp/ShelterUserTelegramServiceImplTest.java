package pro.sky.CWTBshelter.service.imp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.CWTBshelter.model.BotState;
import pro.sky.CWTBshelter.model.ShelterUserTelegram;
import pro.sky.CWTBshelter.model.ShelterUserType;
import pro.sky.CWTBshelter.repository.ShelterUserTelegramRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ShelterUserTelegramServiceImplTest {
    @Mock
    private ShelterUserTelegramRepository shelterUserTelegramRepository;
    @InjectMocks
    private ShelterUserTelegramServiceImp shelterUserTelegramService;

    private final ShelterUserTelegram userTelegram1 = new ShelterUserTelegram(
            1L, 1L, BotState.START, "username1", "usersurname1",
            "+7-999-999-99-99", LocalDate.now(), ShelterUserType.JUST_LOOKING, null
    );
    private final ShelterUserTelegram userTelegramToAdd1 = new ShelterUserTelegram(
            null, 1L, BotState.START, "username1", "usersurname1",
            "+7-999-999-99-99", LocalDate.now(), ShelterUserType.JUST_LOOKING, null
    );
    private final ShelterUserTelegram userTelegram2 = new ShelterUserTelegram(
            2L, 2L, BotState.START, "username2", "usersurname2",
            "+7-988-888-88-88", LocalDate.now(), ShelterUserType.JUST_LOOKING, null
    );

    private final List<ShelterUserTelegram> userTelegrams = List.of(userTelegram1, userTelegram2);

    @Test
    public void add() {
        when(shelterUserTelegramRepository.save(any(ShelterUserTelegram.class))).thenReturn(userTelegram1);

        ShelterUserTelegram actual = shelterUserTelegramService.add(userTelegramToAdd1);

        assertEquals(userTelegram1, actual);
    }

    @Test
    public void findById() {
        when(shelterUserTelegramRepository.findById(anyLong())).thenReturn(Optional.of(userTelegram1));

        ShelterUserTelegram actual = shelterUserTelegramService.findById(userTelegram1.getId());

        assertEquals(userTelegram1, actual);
    }

    @Test
    public void findAll() {
        when(shelterUserTelegramRepository.findAll()).thenReturn(userTelegrams);

        List<ShelterUserTelegram> actual = shelterUserTelegramService.findAll();

        assertIterableEquals(userTelegrams, actual);
    }

    @Test
    public void update() {
        when(shelterUserTelegramRepository.existsById(anyLong())).thenReturn(true);
        when(shelterUserTelegramRepository.save(any(ShelterUserTelegram.class))).thenReturn(userTelegram1);

        ShelterUserTelegram actual = shelterUserTelegramService.update(userTelegram1);

        assertEquals(userTelegram1, actual);
    }

    @Test
    public void removeById() {
        when(shelterUserTelegramRepository.existsById(anyLong())).thenReturn(true);
        assertDoesNotThrow(() -> shelterUserTelegramService.update(userTelegram1));
    }

    @Test
    public void setPhoneNumber() {
        when(shelterUserTelegramRepository.findById(anyLong())).thenReturn(Optional.of(userTelegram1));
        assertDoesNotThrow(() -> shelterUserTelegramService.setPhoneNumber(userTelegram1.getId(), "+7-987-654-32-10"));
    }
}
