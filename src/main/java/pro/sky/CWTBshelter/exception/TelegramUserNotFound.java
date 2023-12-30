package pro.sky.CWTBshelter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Пользователь телеграмма не найден...")

public class TelegramUserNotFound extends RuntimeException {
    public TelegramUserNotFound() {
        super("Пользователь телеграмма не найден...");
    }
}
