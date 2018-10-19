package lt.vilnius.saugus.saugusvilnius;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping(value = "/")
    public String helloWorld() {
        return "Hello world!";
    }

}
