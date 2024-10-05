package app.controllers;

import app.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private UserDao userDao;

    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("people", userDao.getAll());
        return "people/getAll";
    }

    @GetMapping("/detail")
    public String getById(@RequestParam(name = "nickName") String  nickName, Model model) {
        model.addAttribute("person", userDao.get(nickName));
        return "people/getById";
    }
}
