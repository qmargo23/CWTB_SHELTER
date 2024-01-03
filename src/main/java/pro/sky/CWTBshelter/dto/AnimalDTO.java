package pro.sky.CWTBshelter.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

public enum AnimalDTO {
    ;

    public enum Request {
        ;

        @Schema(name = "AnimalCreate")
        @Data
        @AllArgsConstructor
        public static final class Create {
            private String typeAnimal;
            private String breed;
            private Boolean inShelter;
            private Boolean health;
            private Long shelterUser;
        }
    }

    public enum Response {
        ;

        @Schema(name = "AnimalDetail")
        @Data
        @AllArgsConstructor
        public static final class Detail {
            private Long id;
            private String typeAnimal;
            private String breed;
            private Boolean inShelter;
            private Boolean health;
            private Long shelterUser;
        }

        @Schema(name = "AnimalItem")
        @Data
        @AllArgsConstructor
        public static final class Item {
            private Long id;
            private String typeAnimal;
            private String breed;
        }
    }
}
