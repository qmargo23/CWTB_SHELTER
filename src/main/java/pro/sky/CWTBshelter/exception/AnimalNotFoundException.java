package pro.sky.CWTBshelter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Питомец не найден...")
public class AnimalNotFoundException extends RuntimeException {

    public AnimalNotFoundException() {
        super("Питомец не найден...");
    }
}

