package pro.sky.CWTBshelter.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public enum AnimalDTO {
    ;

    public enum Request {
        ;

        @Schema(name = "AnimalCreate")
        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static final class Create {
            private String typeAnimal;
            private String breed;
            private Boolean inShelter;
            private Boolean health;
        }
    }

    public enum Response {
        ;

        @Schema(name = "AnimalDetail")
        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static final class Detail {
            private Long id;
            private String typeAnimal;
            private String breed;
            private Boolean inShelter;
            private Boolean health;
        }

        @Schema(name = "AnimalItem")
        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static final class Item {
            private Long id;
            private String typeAnimal;
            private String breed;
        }
    }
}
