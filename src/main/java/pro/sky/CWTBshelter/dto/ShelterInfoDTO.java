package pro.sky.CWTBshelter.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

public enum ShelterInfoDTO {
    ;

    public enum Request {
        ;

        @Schema(name = "ShelterInfoCreate")
        @Data
        @AllArgsConstructor
        public static final class Create {
            private String aboutShelter;
            private String addressSchedule;
            private String contactForCarPass;
            private String safetyOnTerritory;
            private String firstMeetRecommendation;
            private String documents;
            private String transportationAdvice;
            private String houseRulesForSmallAnimal;
            private String houseRulesForAdultAnimal;
            private String rulesForAnimalWithDisability;
            private String cynologistAdvice;
            private String cynologists;
            private String refuseReasons;
        }
    }

    public enum Response {
        ;

        @Schema(name = "ShelterInfoDetail")
        @Data
        @AllArgsConstructor
        public static final class Detail {
            private Long id;
            private String aboutShelter;
            private String addressSchedule;
            private String contactForCarPass;
            private String safetyOnTerritory;
            private String firstMeetRecommendation;
            private String documents;
            private String transportationAdvice;
            private String houseRulesForSmallAnimal;
            private String houseRulesForAdultAnimal;
            private String rulesForAnimalWithDisability;
            private String cynologistAdvice;
            private String cynologists;
            private String refuseReasons;
        }
    }
}
