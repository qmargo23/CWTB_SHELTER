package pro.sky.CWTBshelter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.CWTBshelter.model.Report;
import pro.sky.CWTBshelter.service.ReportService;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/report")
@Tag(name = "ReportController", description = "CRUD операции по работе с отчетами")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    @Operation(summary = "Создать новый отчет", description = "Введите данные отчета в формате JSON")
    @ApiResponse(responseCode = "200", description = "Отчет создан", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Report.class))
    })
    public ResponseEntity<Report> createReport(@RequestBody Report report) {
        return ResponseEntity.ok(reportService.createReport(report));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить отчет по идентификатору", description = "Введите идентификатор отчета")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Отчет найден", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Report.class))
            }),
            @ApiResponse(responseCode = "404", description = "Отчет не найден"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    public ResponseEntity<Report> getReportById(@Parameter(description = "Идентификатор отчета", example = "1") @PathVariable long id) {
        Report report = reportService.getReportById(id);
        if (report == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(report);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить отчет по идентификатору", description = "Введите идентификатор отчета и обновленные данные в формате JSON.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Отчет обновлен", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Report.class))
            }),
            @ApiResponse(responseCode = "400", description = "Неверные параметры запроса"),
            @ApiResponse(responseCode = "404", description = "Отчет не найден"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    public ResponseEntity<Report> updateReport(@Parameter(description = "Идентификатор отчета", example = "1") @RequestBody Report updatedReport) {
        Report report = reportService.updateReport(updatedReport);
        if (report == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(report);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить отчет по идентификатору", description = "Введите идентификатор отчета, который необходимо удалить")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Отчет удален.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Report.class))
            }),
            @ApiResponse(responseCode = "404", description = "Отчет не найден"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    public ResponseEntity<Void> deleteReportById(@Parameter(description = "Идентификатор отчета", example = "1") @PathVariable long id) {
        if (reportService.deleteReportById(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    @Operation(summary = "Получить все отчеты", description = "Получить список всех отчетов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Отчеты найдены", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Report.class))) }),
            @ApiResponse(responseCode = "404", description = "Отчетов не найдено"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера") })
    public ResponseEntity<List<Report>> getAllReports() {
        List <Report> reports = reportService.getAllReports();
        if (reports.isEmpty()) { return ResponseEntity.notFound().build();
        }
        else { return ResponseEntity.ok(reports);
        }
    }
}