package pro.sky.CWTBshelter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Пользователь приюта не найден...")
public class ShelterUserNotFoundException extends RuntimeException {
    public ShelterUserNotFoundException() {
        super("Пользователь приюта не найден...");
    }
}
