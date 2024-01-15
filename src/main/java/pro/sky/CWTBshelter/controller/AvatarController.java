package pro.sky.CWTBshelter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.CWTBshelter.model.Avatar;
import pro.sky.CWTBshelter.service.imp.AvatarServiceImp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("/avatar")
@Tag(name = "AvatarController", description = "для работы с изображением.")
public class AvatarController {
    private final AvatarServiceImp avatarServiceImp;

    public AvatarController(AvatarServiceImp avatarServiceImp) {
        this.avatarServiceImp = avatarServiceImp;
    }

    @PostMapping(value = "/{id}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "МЕТОД uploadAvatar: Добавить изображение адреса питомника", description = "Введите id приюта и загрузите нужное избражение")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Изображение добавлено"),
            @ApiResponse(responseCode = "400", description = "Параметры запроса имеют некорректный формат или размер."),
            @ApiResponse(responseCode = "404", description = "Приют не найден"),
            @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    })
    public ResponseEntity<String> uploadAvatar(@Parameter(description = "id", example = "1")
                                               @PathVariable Long id, @RequestParam MultipartFile avatar) throws IOException {
        if (avatar.getSize() >= 1024 * 300) {
            return ResponseEntity.badRequest().body("Файл слишком большой");
        }
        avatarServiceImp.uploadAvatar(id, avatar);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{id}/avatar/data")
    @Operation(summary = "МЕТОД downloadAvatarPre: Показать превью адреса питомника", description = "Введите id приюта")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Изображение приюта показано"),
            @ApiResponse(responseCode = "404", description = "Изображение не найдено или сам приют не найден"),
            @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    })
    public ResponseEntity<byte[]> downloadAvatarPre(@Parameter(description = "id", example = "1")
                                                    @PathVariable Long id) {
        Avatar avatar = avatarServiceImp.findAvatar(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        headers.setContentLength(avatar.getData().length);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getData());

    }

    @GetMapping(value = "/{id}/avatar")
    @Operation(summary = "МЕТОД downloadAvatar: Показать изображение адреса питомника", description = "Введите id приюта")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Изображение приюта показано"),
            @ApiResponse(responseCode = "404", description = "Изображение приюта не найдено или сам приют не найден"),
            @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    })
    public void downloadAvatar(@Parameter(description = "id", example = "1")
                               @PathVariable Long id, HttpServletResponse response) throws IOException {
        Avatar avatar = avatarServiceImp.findAvatar(id);

        Path path = Path.of(avatar.getFilePath());

        try (InputStream is = Files.newInputStream(path);
             OutputStream os = response.getOutputStream();) {
            response.setStatus(200);
            response.setContentType(avatar.getMediaType());
            response.setContentLength((int) avatar.getFileSize());
            is.transferTo(os);

        }

    }
}