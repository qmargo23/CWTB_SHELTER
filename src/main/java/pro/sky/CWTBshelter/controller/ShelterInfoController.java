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
import pro.sky.CWTBshelter.model.ShelterInfo;
import pro.sky.CWTBshelter.service.ShelterInfoService;

import java.util.List;

@RestController
@RequestMapping("/shelter")
@Tag(name = "ShelterInfoController", description = "CRUD-операции и другие эндпоинты для работы с питомником")
public class ShelterInfoController {
    private final ShelterInfoService shelterInfoService;

    public ShelterInfoController(ShelterInfoService shelterInfoService) {
        this.shelterInfoService = shelterInfoService;
    }

    @PostMapping
    @Operation(summary = "МЕТОД addShelterInfo: Добавить новый питомник", description = "Введите данные в формате JSON")
    @ApiResponse(responseCode = "200", description = "Питомник добавлен", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ShelterInfo.class)))
    })
    public ResponseEntity<ShelterInfo> addShelterInfo(@RequestBody ShelterInfo shelterInfo) {
        return ResponseEntity.ok(shelterInfoService.createShelterInfo(shelterInfo));
    }

    @GetMapping("/{id}")
    @Operation(summary = "МЕТОД getShelterInfoById: Получить информацию о питомнике по его id", description = "Введите id питомника")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Информация о питомнике получена", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ShelterInfo.class)))}),
            @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат"),
            @ApiResponse(responseCode = "404", description = "Питомник не найден"),
            @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    })
    public ResponseEntity<ShelterInfo> getShelterInfoById(@Parameter(description = "   id", example = "1")
                                                          @PathVariable long id) {
        ShelterInfo shelterById = shelterInfoService.findShelterInfoById(id);
        if (shelterById == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(shelterById);
        }
    }

    @PutMapping
    @Operation(summary = "МЕТОД editAnimal: Отредактировать данные питомника", description = "Введите id питомника и его данные в формате JSON")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные о питомнике отредактированы", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ShelterInfo.class)))}),
            @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат"),
            @ApiResponse(responseCode = "404", description = "Питомник не найден"),
            @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    })
    public ResponseEntity<ShelterInfo> editShelterInfo(@RequestBody ShelterInfo shelters) {

        ShelterInfo shelterInfo = shelterInfoService.editShelterInfo(shelters);
        if (shelterInfo == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(shelterInfo);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "МЕТОД deleteAnimal: Удалить питомник из базы данных", description = "Необходимо указать id питомника, которого нужно удалить")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Питомник удален", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ShelterInfo.class)))}),
            @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат"),
            @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")

    })
    public ResponseEntity<Void> deleteShelterInfo(@PathVariable long id) {
        if (shelterInfoService.deleteShelterInfoById(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    @Operation(summary = "МЕТОД getAllShelters: Получить список всех питомников")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список приютов получен", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ShelterInfo.class)))
            }),
            @ApiResponse(responseCode = "404", description = "Ничего не найдено"),
            @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    })
    public ResponseEntity<List<ShelterInfo>> getAllShelters() {
        List<ShelterInfo> shelters = shelterInfoService.getAllShelters();
        if (shelters.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(shelters);
    }
    //методы Мирослава

    @GetMapping("/first-meet")
    @Operation(summary = "getFirstMeetRecommendation Метод : Получить рекомендации при первом знакомстве с животным.")
    @ApiResponses(value =
            {@ApiResponse(responseCode = "200", description = "Рекомендация при первом знакомстве с животным",
                    content = {
                            @Content(mediaType = "text/plain", schema =
                            @Schema(type = "string"))
                    }),
                    @ApiResponse(responseCode = "404", description = "Рекомендации не найдены"),
                    @ApiResponse(responseCode = "500", description = "Произошла ошибка не зависящая от вызывающей стороны")})
    public ResponseEntity<String> getFirstMeetRecommendation() {
        String recommendation = shelterInfoService.getFirstMeetRecommendation();
        if (recommendation != null){
            return ResponseEntity.ok(recommendation);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/refuse-reasons")
    @Operation(summary = "getRefuseReasons Метод : Причины по которым могут отказать и не дать забрать животное из приюта.")
    @ApiResponses(value =
            {@ApiResponse(responseCode = "200", description = "Причины по которым могут отказать и не дать забрать животное из приюта.",
                    content = {
                            @Content(mediaType = "text/plain", schema =
                            @Schema(type = "string"))
                    }),
                    @ApiResponse(responseCode = "404", description = "Причины не найдены"),
                    @ApiResponse(responseCode = "500", description = "Произошла ошибка не зависящая от вызывающей стороны")})
    public ResponseEntity<String> getRefuseReasons() {
        String reasons = shelterInfoService.getRefuseReasons();
        if (reasons != null){
            return ResponseEntity.ok(reasons);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/safety-on-territory")
    @Operation(summary = "getSafetyOnTerritory Метод : Общие рекомендации по технике безопасности на территории приюта.")
    @ApiResponses(value =
            {@ApiResponse(responseCode = "200", description = "Общие рекомендации по технике безопасности на территории приюта.",
                    content = {
                            @Content(mediaType = "text/plain", schema =
                            @Schema(type = "string"))
                    }),
                    @ApiResponse(responseCode = "404", description = "Рекомендации не найдены"),
                    @ApiResponse(responseCode = "500", description = "Произошла ошибка не зависящая от вызывающей стороны")})
    public ResponseEntity<String> getSafetyOnTerritory() {
        String recommendation = shelterInfoService.getSafetyOnTerritory();
        if (recommendation != null){
            return ResponseEntity.ok(recommendation);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
