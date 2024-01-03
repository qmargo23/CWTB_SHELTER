package pro.sky.CWTBshelter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Нельзя выбрать пользователя приюта с таким id")
public class BadShelterUserIdException extends RuntimeException {
    public BadShelterUserIdException() {
    }
}
