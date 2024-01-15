package pro.sky.CWTBshelter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Изображение для данного приюта не найдено...")
public class AvatarNotFoundException extends RuntimeException{
    public AvatarNotFoundException() {
        super("Изображение для данного приюта не найдено...");
    }
}
