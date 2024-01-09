package pro.sky.CWTBshelter.service;

import org.springframework.web.multipart.MultipartFile;
import pro.sky.CWTBshelter.model.Avatar;

import java.io.IOException;
import java.nio.file.Path;

public interface AvatarService {
    void uploadAvatar(Long id, MultipartFile avatar) throws IOException;

    byte[] generateImagePreview(Path filePath) throws IOException;

    Avatar findAvatar(Long id);

    String getExtension(String fileName);
}
