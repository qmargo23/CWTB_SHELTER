package pro.sky.CWTBshelter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Пользователь приюта не найден...")
public class ShelterUserTelegramNotFoundException extends RuntimeException {
    public ShelterUserTelegramNotFoundException() {
        super("Пользователь приюта не найден...");
    }
}