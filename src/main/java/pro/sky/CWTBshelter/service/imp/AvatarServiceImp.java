package pro.sky.CWTBshelter.service.imp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.CWTBshelter.model.Avatar;
import pro.sky.CWTBshelter.model.ShelterInfo;
import pro.sky.CWTBshelter.repository.AvatarRepository;
import pro.sky.CWTBshelter.service.AvatarService;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional

public class AvatarServiceImp implements AvatarService {
    @Value("avatars")
    private String avatarsDir;
    private final ShelterInfoServiceImpl shelterInfoService;
    private final AvatarRepository avatarRepository;

    public AvatarServiceImp(ShelterInfoServiceImpl shelterInfoService,
                            AvatarRepository avatarRepository) {
        this.shelterInfoService = shelterInfoService;
        this.avatarRepository = avatarRepository;
    }

    @Override
    public void uploadAvatar(Long id, MultipartFile file) throws IOException {

        ShelterInfo result = shelterInfoService.findShelterInfoById(id);

        Path filePath = Path.of(avatarsDir, id + "." + getExtension(Objects.requireNonNull(file.getOriginalFilename())));

        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (InputStream is = file.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }
        Avatar avatar = findAvatar(id);

        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(file.getSize());
        avatar.setMediaType(file.getContentType());
        avatar.setData(generateImagePreview(filePath));
        avatar.setShelterInfo(result);
//сохранили данные каритнки в БД
        avatarRepository.save(avatar);

    }

    @Override
    public byte[] generateImagePreview(Path filePath) throws IOException {
        try (InputStream is = Files.newInputStream(filePath);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            BufferedImage image = ImageIO.read(bis);

            int height = image.getHeight() / (image.getWidth() / 100);
            BufferedImage preview = new BufferedImage(100, height, image.getType());
            Graphics2D graphics = preview.createGraphics();
            graphics.drawImage(image, 0, 0, 100, height, null);
            graphics.dispose();

            ImageIO.write(preview, getExtension(filePath.getFileName().toString()), baos);
            return baos.toByteArray();
        }
    }

    @Override
    public Avatar findAvatar(Long id) {
        return avatarRepository.findByShelterInfoId(id).orElse(new Avatar());
    }

    @Override
    public String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
