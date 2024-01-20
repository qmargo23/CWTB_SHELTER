package pro.sky.CWTBshelter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.CWTBshelter.model.Animal;
import pro.sky.CWTBshelter.model.ReportTelegram;
import pro.sky.CWTBshelter.model.ShelterUser;
import pro.sky.CWTBshelter.service.ReportTelegramService;

import java.util.List;

@RestController
@RequestMapping("/reportsTelegram")
@Tag(name = "Отчеты о питомцах", description = "CRUD-операции и другие эндпоинты для работы с отчетами")
public class ReportTelegramController {
    private final ReportTelegramService reportTelegramService;
    public ReportTelegramController(ReportTelegramService reportTelegramService) {
        this.reportTelegramService = reportTelegramService;
    }

    @PostMapping
    @Operation(summary = "Добавить новый отчет", description = "Введите данные")
    @ApiResponse(responseCode = "200", description = "Отчет добавлен", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ReportTelegram.class)))
    })
    public ResponseEntity<ReportTelegram> addTelegramReport(@RequestBody ReportTelegram reportTelegram) {

        return ResponseEntity.ok(reportTelegramService.createTelegramReport(reportTelegram));
    }

    @GetMapping
    @Operation(summary = "Получить список всех отчетов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список отчетов получен", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ReportTelegram.class)))
            }),
            @ApiResponse(responseCode = "404", description = "Ничего не найдено"),
            @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    })
    public ResponseEntity<List<ReportTelegram>> getAllTelegramReports() {
        List<ReportTelegram> reportTelegrams = reportTelegramService.getAllTelegramReports();
        if (reportTelegrams.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reportTelegrams);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить отчет по его id", description = "Введите id отчета")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Отчет получен", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ShelterUser.class)))}),
            @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат"),
            @ApiResponse(responseCode = "404", description = "Животное не найдено"),
            @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    })
    public ResponseEntity<ReportTelegram> getReportTelegramById(@PathVariable long id) {
        ReportTelegram reportTelegramById = reportTelegramService.findTelegramReportById(id);
        if (reportTelegramById == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(reportTelegramById);
        }
    }

    @PutMapping
    @Operation(summary = "Отредактировать отчет", description = "Введите id отчета и новые данные")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Отчет отредактирован", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ReportTelegram.class)))}),
            @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат"),
            @ApiResponse(responseCode = "404", description = "Отчет не найден"),
            @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    })
    public ResponseEntity<ReportTelegram> editTelegramReport(@RequestBody ReportTelegram reportTelegram) {
        ReportTelegram editReportTelegram = reportTelegramService.editTelegramReport(reportTelegram);
        if (editReportTelegram == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(editReportTelegram);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить отчет из базы данных", description = "Необходимо указать id отчета, который нужно удалить")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Отчет удален", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Animal.class)))}),
            @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат"),
            @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    })
    public ResponseEntity<Void> deleteTelegramReport(@PathVariable long id) {
        if (reportTelegramService.deleteTelegramReportById(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}