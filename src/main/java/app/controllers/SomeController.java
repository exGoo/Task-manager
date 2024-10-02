package app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/guest")
public class SomeController {

    @GetMapping("/hello")
    public String hello() {
        return "guest/hello";
    }

    @GetMapping("/calculate")
    public String calculate(@RequestParam("a") Integer a,
                            @RequestParam("b") Integer b,
                            @RequestParam("operator") String operator,
                            Model model) {

        int result = 0;
        switch (operator) {
            case "add":
                result = a + b;
                break;
            case "sub":
                result = a - b;
                break;
            case "mul":
                result = a * b;
                break;
            case "div":
                result = a / b;
                break;
            default:
                result = 0;
                break;
        }

        model.addAttribute("result", result);
        return "guest/calculate";
    }

    @GetMapping("/goodbye")
    public String goodByePage() {
        return "guest/goodbye";
    }
}
