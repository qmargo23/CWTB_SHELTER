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
import pro.sky.CWTBshelter.dto.ShelterInfoDTO;
import pro.sky.CWTBshelter.dto.mapper.ShelterInfoDTOMapper;
import pro.sky.CWTBshelter.service.ShelterInfoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/shelter")
@Tag(name = "ShelterInfoController", description = "CRUD-операции и другие эндпоинты для работы с питомником")
public class ShelterInfoController {
    private final ShelterInfoService shelterInfoService;
    private final ShelterInfoDTOMapper shelterInfoDTOMapper;

    public ShelterInfoController(ShelterInfoService shelterInfoService, ShelterInfoDTOMapper shelterInfoDTOMapper) {
        this.shelterInfoService = shelterInfoService;
        this.shelterInfoDTOMapper = shelterInfoDTOMapper;
    }

    @PostMapping
    @Operation(summary = "МЕТОД addShelterInfo: Добавить новый питомник", description = "Введите данные в формате JSON")
    @ApiResponse(responseCode = "200", description = "Питомник добавлен", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ShelterInfoDTO.Response.Detail.class)))
    })
    public ResponseEntity<ShelterInfoDTO.Response.Detail> addShelterInfo(@RequestBody ShelterInfoDTO.Request.Create request) {
        return ResponseEntity.ok(shelterInfoDTOMapper.toDetailDTO(shelterInfoService.createShelterInfo(request)));
    }

    @GetMapping("/{id}")
    @Operation(summary = "МЕТОД getShelterInfoById: Получить информацию о питомнике по его id", description = "Введите id питомника")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Информация о питомнике получена", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ShelterInfoDTO.Response.Detail.class)))}),
            @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат"),
            @ApiResponse(responseCode = "404", description = "Питомник не найден"),
            @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    })
    public ResponseEntity<ShelterInfoDTO.Response.Detail> getShelterInfoById(@Parameter(description = "   id", example = "1") @PathVariable long id) {
        return ResponseEntity.ok(shelterInfoDTOMapper.toDetailDTO(shelterInfoService.findShelterInfoById(id)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "МЕТОД editAnimal: Отредактировать данные питомника", description = "Введите id питомника и его данные в формате JSON")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные о питомнике отредактированы", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ShelterInfoDTO.Response.Detail.class)))}),
            @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат"),
            @ApiResponse(responseCode = "404", description = "Питомник не найден"),
            @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    })
    public ResponseEntity<ShelterInfoDTO.Response.Detail> editShelterInfo(
            @PathVariable long id,
            @RequestBody ShelterInfoDTO.Request.Create request
    ) {
        return ResponseEntity.ok(shelterInfoDTOMapper.toDetailDTO(shelterInfoService.editShelterInfo(id, request)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "МЕТОД deleteAnimal: Удалить питомник из базы данных", description = "Необходимо указать id питомника, которого нужно удалить")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Питомник удален"),
            @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат"),
            @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    })
    public ResponseEntity<Void> deleteShelterInfo(@PathVariable long id) {
        shelterInfoService.deleteShelterInfoById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "МЕТОД getAllShelters: Получить список всех питомников")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список приютов получен", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ShelterInfoDTO.Response.Detail.class)))
            }),
            @ApiResponse(responseCode = "404", description = "Ничего не найдено"),
            @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    })
    public ResponseEntity<List<ShelterInfoDTO.Response.Detail>> getAllShelters() {
        return ResponseEntity.ok(shelterInfoService.getAllShelters()
                .stream()
                .map(shelterInfoDTOMapper::toDetailDTO)
                .collect(Collectors.toList())
        );
    }
    // Методы Коли
    @GetMapping("/car-pass/contact")
    @Operation(summary = "getContactForCarPass МЕТОД: Получить контакт для получения пропуска на автомобиль.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Контактное лицо для получения пропуска на автомобиль", content = {
                    @Content(mediaType = "text/plain", schema = @Schema(type = "string"))
            }),
            @ApiResponse(responseCode = "404", description = "Контакт не найден"),
            @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    })
    public ResponseEntity<String> getContactForCarPass() {
        String contact = shelterInfoService.getContactForCarPass();
        if (contact != null) {
            return ResponseEntity.ok(contact);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/documents")
    @Operation(summary = "getDocuments МЕТОД: Получить документы необходимые для того, чтобы взять животное из приюта")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Документы получены", content = {
                    @Content(mediaType = "text/plain", schema = @Schema(type = "string"))
            }),
            @ApiResponse(responseCode = "404", description = "Документы не найдены"),
            @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    })
    public ResponseEntity<String> getDocuments() {
        String documents = shelterInfoService.getDocuments();
        if (documents != null) {
            return ResponseEntity.ok(documents);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/transportation-advice")
    @Operation(summary = "Get transportation advice МЕТОД: Получение рекомендаций по транспортировке питомца")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Рекомендации по транспортировке получены", content = {
                    @Content(mediaType = "text/plain", schema = @Schema(type = "string"))
            }),
            @ApiResponse(responseCode = "404", description = "Рекомендации по транспортировке не найдены"),
            @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    })
    public ResponseEntity<String> getTransportationAdvice() {
        String transportationAdvice = shelterInfoService.getTransportationAdvice();
        if (transportationAdvice != null) {
            return ResponseEntity.ok(transportationAdvice);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

