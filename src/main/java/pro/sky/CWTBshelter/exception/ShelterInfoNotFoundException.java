package pro.sky.CWTBshelter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Питомник не найден...")
public class ShelterInfoNotFoundException extends RuntimeException {
    public ShelterInfoNotFoundException() {
        super("Питомник не найден...");
    }
}
