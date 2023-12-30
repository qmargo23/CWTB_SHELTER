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
import pro.sky.CWTBshelter.dto.AnimalDTO;
import pro.sky.CWTBshelter.dto.mapper.AnimalDTOMapper;
import pro.sky.CWTBshelter.service.AnimalService;
import pro.sky.CWTBshelter.service.ShelterUserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/animal")
@Tag(name = "AnimalController", description = "CRUD-операции и другие эндпоинты для работы с животными")
public class AnimalController {
    private final AnimalService animalService;
    private final ShelterUserService shelterUserService;
    private final AnimalDTOMapper mapper;

    public AnimalController(AnimalService animalService, ShelterUserService shelterUserService, AnimalDTOMapper mapper) {
        this.animalService = animalService;
        this.shelterUserService = shelterUserService;
        this.mapper = mapper;
    }

    @PostMapping
    @Operation(summary = "МЕТОД addAnimal: Добавить новое животное", description = "Введите данные в формате JSON")
    @ApiResponse(responseCode = "200", description = "Питомец добавлен", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = AnimalDTO.Response.Detail.class))
    })
    public ResponseEntity<AnimalDTO.Response.Detail> addAnimal(@RequestBody AnimalDTO.Request.Create request) {
        return ResponseEntity.ok(mapper.toDetailDTO(animalService.createAnimal(request)));
    }

    @GetMapping("/{id}")
    @Operation(summary = "МЕТОД getAnimalById: Получить информацию о питомце по его id", description = "Введите id животного")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Информация о животном получена", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AnimalDTO.Response.Detail.class))}),
            @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат"),
            @ApiResponse(responseCode = "404", description = "Животное не найдено"),
            @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    })
    public ResponseEntity<AnimalDTO.Response.Detail> getAnimalById(@Parameter(description = "id", example = "1") @PathVariable long id) {
        return ResponseEntity.ok(mapper.toDetailDTO(animalService.findAnimalById(id)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "МЕТОД editAnimal: Отредактировать данные питомца", description = "Введите id питомца и его данные в формате JSON")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные о животном отредактированы", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AnimalDTO.Response.Detail.class))}),
            @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат"),
            @ApiResponse(responseCode = "404", description = "Животное не найдено"),
            @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    })
    public ResponseEntity<AnimalDTO.Response.Detail> editAnimal(
            @PathVariable long id,
            @RequestBody AnimalDTO.Request.Create request
    ) {
        return ResponseEntity.ok(mapper.toDetailDTO(animalService.editAnimal(id, request)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "МЕТОД deleteAnimal: Удалить питомца из базы данных", description = "Необходимо указать id питомца, которого нужно удалить")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Животное удалено"),
            @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат"),
            @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")

    })
    public ResponseEntity<Void> deleteAnimal(@PathVariable long id) {
        animalService.deleteAnimalById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "МЕТОД getAllAnimals: Получить список всех питомцев в приюте")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список животных получен", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = AnimalDTO.Response.Item.class)))
            }),
            @ApiResponse(responseCode = "404", description = "Ничего не найдено"),
            @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    })
    public ResponseEntity<List<AnimalDTO.Response.Item>> getAllAnimals() {
        return ResponseEntity.ok(animalService.getAllAnimals().stream()
                .map(mapper::toItemDTO)
                .collect(Collectors.toList())
        );
    }
}