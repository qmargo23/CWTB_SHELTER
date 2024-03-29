package pro.sky.CWTBshelter.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public enum ReportDTO {
    ;

    public enum Request {
        ;

        @Schema(name = "ReportCreate")
        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static final class Create {
            private String photo;
            private LocalDate localDate;
            private String reportTextUnderPhoto;
        }
    }

    public enum Response {
        ;

        @Schema(name = "ReportDetail")
        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static final class Detail {
            private Long id;
            private String photo;
            private LocalDate localDate;
            private String reportTextUnderPhoto;
        }
    }
}
