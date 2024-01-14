package pro.sky.CWTBshelter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.CWTBshelter.model.ShelterUser;
import pro.sky.CWTBshelter.model.ShelterUserTelegram;
import pro.sky.CWTBshelter.service.ShelterUserTelegramService;

import java.util.List;

@RestController
@RequestMapping("/user-telegram")
@Tag(name = "ShelterUserTelegramController", description = "CRUD операции по работе с пользователями приюта")
public class ShelterUserTelegramController {
    private final ShelterUserTelegramService service;

    public ShelterUserTelegramController(ShelterUserTelegramService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    @Operation(summary = "Получить пользователя по идентификатору", description = "Введите идентификатор пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь найден", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ShelterUser.class))
            }),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    public ResponseEntity<ShelterUserTelegram> getShelterUserTelegramById(@Parameter(description = "Идентификатор пользователя", example = "1") @PathVariable long id) {
        ShelterUserTelegram shelterUser = service.findById(id);
        if (shelterUser == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(shelterUser);
        }
    }

    @GetMapping
    @Operation(summary = "Получить список всех пользователей", description = "Получить список всех пользователей")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователи найдены", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ShelterUser.class)))
            }),
            @ApiResponse(responseCode = "404", description = "Ничего не найдено"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    public ResponseEntity<List<ShelterUserTelegram>> getAllShelterUsersTelegram() {
        List<ShelterUserTelegram> allSheltersUsers = service.findAll();
        if (allSheltersUsers.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(allSheltersUsers);
    }

    @PostMapping
    @Operation(summary = "Добавить нового пользователя", description = "Создание нового пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь создан", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ShelterUser.class))
            }),
            @ApiResponse(responseCode = "400", description = "Неверные параметры запроса"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    public ResponseEntity<ShelterUserTelegram> createShelterUserTelegram(@RequestBody ShelterUserTelegram shelterUser) {

        return ResponseEntity.ok(service.add(shelterUser));
    }

    @PutMapping("{id}")
    @Operation(summary = "Обновить пользователя по идентификатору", description = "Введите идентификатор пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь обновлен", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ShelterUser.class))
            }),
            @ApiResponse(responseCode = "400", description = "Неверные параметры запроса"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    public ResponseEntity<ShelterUserTelegram> updateShelterUserTelegram(
            @Parameter(description = "Идентификатор пользователя", example = "1") @PathVariable long id,
            @RequestBody ShelterUserTelegram shelterUser
    ) {
        ShelterUserTelegram editShelterUser = service.update(shelterUser);
        if (editShelterUser == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(shelterUser);
        }
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удалить пользователя по идентификатору", description = "Введите идентификатор пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Пользователь удален"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    public ResponseEntity<Void> deleteShelterUserTelegram(@Parameter(description = "Идентификатор пользователя", example = "1")
                                                  @PathVariable long id) {
        if (service.removeById(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}/phone-number-from-telegram")
    @Operation(summary = "Задать номер телефона для пользователя приюта", description = "Введите номер телефона")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Номер телефона задан успешно"),
            @ApiResponse(responseCode = "404", description = "Пользователь приюта не найден или Некорректный номер телефона")
    })
    public ResponseEntity<Void> setPhoneNumberTelegram(@PathVariable Long id, @RequestParam String phoneNumber) {
        boolean success = service.setPhoneNumber(id, phoneNumber);
        if (success) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
