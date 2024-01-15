package pro.sky.CWTBshelter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Нельзя выбрать телеграм пользователя с таким id")
public class BadTelegramUserIdException extends RuntimeException {
    public BadTelegramUserIdException() {
    }
}
