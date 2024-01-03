package pro.sky.CWTBshelter.dto.mapper;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import pro.sky.CWTBshelter.dto.ReportDTO;
import pro.sky.CWTBshelter.model.Report;

@NoArgsConstructor
@Component
public class ReportDTOMapper {
    public ReportDTO.Response.Detail toDetailDTO(Report report) {
        if (report == null) {
            return null;
        }
        return new ReportDTO.Response.Detail(
                report.getId(),
                report.getPhoto(),
                report.getLocalDate(),
                report.getReportTextUnderPhoto()
        );
    }

    public Report toReport(ReportDTO.Request.Create dto) {
        if (dto == null) {
            return null;
        }
        return new Report(
                null,
                dto.getPhoto(),
                dto.getLocalDate(),
                dto.getReportTextUnderPhoto()
        );
    }
}
