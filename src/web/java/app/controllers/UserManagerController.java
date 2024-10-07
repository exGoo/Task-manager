package app.controllers;

import app.dao.UserDao;
import app.dao.UserDaoImp;
import app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/uManager")
public class UserManagerController {

    private final UserDao userDao;

    @Autowired
    public UserManagerController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("users", userDao.getAll());
        return "uManager/list_of_users";
    }

    @GetMapping("/user")
    public String getById(@RequestParam(name = "nickname") String  nickName, Model model) {
        model.addAttribute("user", userDao.get(nickName));
        return "uManager/current_user";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "uManager/new_user";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("user") User user) {
        userDao.persist(user);
        return "redirect:/uManager";
    }

    @DeleteMapping("/delete")
    public String delete(@RequestParam(name = "nickname") String  nickName) {
        userDao.remove(userDao.get(nickName));
        return "redirect:/uManager";
    }
}
