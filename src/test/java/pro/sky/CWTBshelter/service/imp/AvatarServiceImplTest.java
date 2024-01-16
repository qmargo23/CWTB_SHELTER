package pro.sky.CWTBshelter.service.imp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.CWTBshelter.model.Avatar;
import pro.sky.CWTBshelter.repository.AvatarRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AvatarServiceImplTest {
    @Mock
    private AvatarRepository avatarRepository;
    @Mock
    private ShelterInfoServiceImpl shelterInfoService;
    @InjectMocks
    private AvatarServiceImp avatarService;

    @Test
    public void findAvatar() {
        when(avatarRepository.findByShelterInfoId(anyLong())).thenReturn(Optional.of(new Avatar()));

        Avatar actual = avatarService.findAvatar(1L);

        assertNotNull(actual);
    }

    @Test
    public void getExtension() {
        assertEquals("png", avatarService.getExtension("image.png"));
    }
}
