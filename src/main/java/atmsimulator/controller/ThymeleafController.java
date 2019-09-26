package atmsimulator.controller;

import atmsimulator.model.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ThymeleafController {

    @GetMapping("/")
    public String home() {
        return "/home";
    }

    @GetMapping("/home")
    public String home1() {
        return "/home";
    }

    @GetMapping("/signin")
    public String login(Model model) {
        Account account = new Account();
        model.addAttribute("account", account);
        return "/signin";
    }

    @GetMapping("/user")
    public String user() {
        return "/user";
    }


    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }
}
