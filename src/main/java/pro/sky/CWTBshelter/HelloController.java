package pro.sky.CWTBshelter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping//http://localhost:8080/
    public String sayHello() {
        return "Приложение РАБОТАЕТ!";
    }
}
