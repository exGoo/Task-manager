package app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping(value = "/")
    public String hello() {
        return "main_page";
    }

}
