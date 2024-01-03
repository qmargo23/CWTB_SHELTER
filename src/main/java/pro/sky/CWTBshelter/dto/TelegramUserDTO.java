package pro.sky.CWTBshelter.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import pro.sky.CWTBshelter.model.BotState;

public enum TelegramUserDTO {
    ;

    public enum Request {
        ;

        @Schema(name = "TelegramUserCreate")
        @Data
        @AllArgsConstructor
        public static final class Create {
            private Long chatId;
            private BotState botState;
            private Long shelterUser;
        }
    }

    public enum Response {
        ;

        @Schema(name = "TelegramUserDetail")
        @Data
        @AllArgsConstructor
        public static final class Detail {
            private Long id;
            private Long chatId;
            private BotState botState;
            private Long shelterUser;
        }
    }
}
