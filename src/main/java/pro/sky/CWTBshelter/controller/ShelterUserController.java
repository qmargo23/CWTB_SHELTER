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
import pro.sky.CWTBshelter.service.ShelterUserService;

import java.util.List;

@RestController
@RequestMapping("/user")
@Tag(name = "ShelterUserController", description = "CRUD операции по работе с пользователями приюта")
public class ShelterUserController {
    private final ShelterUserService service;

    public ShelterUserController(ShelterUserService service) {
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
    public ResponseEntity<ShelterUser> getShelterUserById(@Parameter(description = "Идентификатор пользователя", example = "1") @PathVariable long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    @Operation(summary = "Получить всех пользователей", description = "Получить список всех пользователей")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователи найдены", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ShelterUser.class)))
            }),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    public ResponseEntity<List<ShelterUser>> getAllShelterUsers() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    @Operation(summary = "Создать нового пользователя", description = "Создание нового пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь создан", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ShelterUser.class))
            }),
            @ApiResponse(responseCode = "400", description = "Неверные параметры запроса"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    public ResponseEntity<ShelterUser> createShelterUser(@RequestBody ShelterUser shelterUser) {
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
    public ResponseEntity<ShelterUser> updateShelterUser(
            @Parameter(description = "Идентификатор пользователя", example = "1") @PathVariable long id,
            @RequestBody ShelterUser shelterUser
    ) {
        shelterUser.setId(id);
        return ResponseEntity.ok(service.update(shelterUser));
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удалить пользователя по идентификатору", description = "Введите идентификатор пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Пользователь удален"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    public ResponseEntity<ShelterUser> deleteShelterUser(@Parameter(description = "Идентификатор пользователя", example = "1") @PathVariable long id) {
        service.removeById(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}/phone-number")
    @Operation(summary = "Задать номер телефона для пользователя приюта", description = "Введите номер телефона")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Номер телефона задан успешно"),
            @ApiResponse(responseCode = "400", description = "Некорректный номер телефона"),
            @ApiResponse(responseCode = "404", description = "Пользователь приюта не найден")
    })
    public ResponseEntity<Void> setPhoneNumber(@PathVariable Long id, @RequestParam String phoneNumber) {
        boolean success = service.setPhoneNumber(id, phoneNumber);
        if (success) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
