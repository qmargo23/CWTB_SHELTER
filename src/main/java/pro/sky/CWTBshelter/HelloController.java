package pro.sky.CWTBshelter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping//http://localhost:8080/
    public String sayHello() {
        return "Приложение  РАБОТАЕТ! " +
                "\n ДЛЯ Заметок или Напоминаний По Коду:" +
                "\n 1 надо заносить данные в БД в одном формате - например только строчные" +
                "\n";
    }
}
