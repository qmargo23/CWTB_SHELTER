package pro.sky.CWTBshelter.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pro.sky.CWTBshelter.model.ShelterUserType;

import java.time.LocalDate;

public enum ShelterUserDTO {
    ;

    public enum Request {
        ;

        @Schema(name = "ShelterUserCreate")
        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static final class Create {
            private String name;
            private String surname;
            private String phoneNumber;
            private LocalDate adoptDate;
            private Long telegramUser;
            private ShelterUserType type;
        }
    }

    public enum Response {
        ;

        @Schema(name = "ShelterUserDetail")
        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static final class Detail {
            private Long id;
            private String name;
            private String surname;
            private String phoneNumber;
            private LocalDate adoptDate;
            private Long telegramUser;
            private ShelterUserType type;
        }

        @Schema(name = "ShelterUserItem")
        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static final class Item {
            private Long id;
            private String name;
            private String surname;
            private String phoneNumber;
        }
    }
}
