package pro.sky.CWTBshelter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.CWTBshelter.service.imp.AvatarServiceImp;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AvatarController.class)
public class AvatarControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private AvatarServiceImp avatarService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void uploadAvatar() throws Exception {
        MockMultipartFile file = new MockMultipartFile("avatar", "test.png", MediaType.MULTIPART_FORM_DATA_VALUE, new byte[1000]);
        mvc.perform(MockMvcRequestBuilders.multipart("/avatar/{id}/avatar", 1L)
                        .file(file))
                .andExpect(status().isOk());
        verify(avatarService, only()).uploadAvatar(anyLong(), any(MultipartFile.class));
    }
}
