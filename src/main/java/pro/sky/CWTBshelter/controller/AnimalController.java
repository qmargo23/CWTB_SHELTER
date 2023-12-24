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
import pro.sky.CWTBshelter.model.Animal;
import pro.sky.CWTBshelter.service.AnimalService;

import java.util.List;

@RestController
@RequestMapping("/animal")
@Tag(name = "AnimalController", description = "CRUD-операции и другие эндпоинты для работы с животными")
public class AnimalController {
    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping
    @Operation(summary = "МЕТОД addAnimal: Добавить новое животное", description = "Введите данные в формате JSON")
    @ApiResponse(responseCode = "200", description = "Питомец добавлен", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Animal.class)))
    })
    public ResponseEntity<Animal> addAnimal(@RequestBody Animal animal) {
        return ResponseEntity.ok(animalService.createAnimal(animal));
    }

    @GetMapping("/{id}")
    @Operation(summary = "МЕТОД getAnimalById: Получить информацию о питомце по его id", description = "Введите id животного")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Информация о животном получена", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Animal.class)))}),
            @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат"),
            @ApiResponse(responseCode = "404", description = "Животное не найдено"),
            @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    })
    public ResponseEntity<Animal> getAnimalById(@Parameter(description = "   id", example = "1")
                                                @PathVariable long id) {

        Animal animalById = animalService.findAnimalById(id);
        if (animalById == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(animalById);
        }
    }

    @PutMapping
    @Operation(summary = "МЕТОД editAnimal: Отредактировать данные питомца", description = "Введите id питомца и его данные в формате JSON")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные о животном отредактированы", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Animal.class)))}),
            @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат"),
            @ApiResponse(responseCode = "404", description = "Животное не найдено"),
            @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    })
    public ResponseEntity<Animal> editAnimal(@RequestBody Animal animals) {

        Animal animal = animalService.editAnimal(animals);
        if (animal == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(animal);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "МЕТОД deleteAnimal: Удалить питомца из базы данных", description = "Необходимо указать id питомца, которого нужно удалить")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Животное удалено", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Animal.class)))}),
            @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат"),
            @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")

    })
    public ResponseEntity<Void> deleteAnimal(@PathVariable long id) {

        if (animalService.deleteAnimalById(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    @Operation(summary = "МЕТОД getAllAnimals: Получить список всех питомцев в приюте")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список животных получен", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Animal.class)))
            }),
            @ApiResponse(responseCode = "404", description = "Ничего не найдено"),
            @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    })
    public ResponseEntity<List<Animal>> getAllAnimals() {
        List<Animal> animals = animalService.getAllAnimals();
        if (animals.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(animals);
    }
}