package coco;

import coco.Models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class LoginControler {
    @Autowired
        private UserRepository userRepository;
    // test
    @GetMapping("/login")
    public String loginScreen (Model model) {
        model.addAttribute("login", new User());
        return "login";
    }

    private @ResponseBody boolean check (User login) {
        if(userRepository.checkUserPasswordCombination(login.getUserName(),login.getPassword()).size() == 1) {
            return true;
        } else {
            return false;
        }
    }
    @PostMapping("/login")
    public String registerOUT(@ModelAttribute User login,Model model, HttpSession session) {
        if(check(login)){
            session.setAttribute("userName", login.getUserName());
            return "loginSuccess";
        } else {
            model.addAttribute("login", new User());
            return "login";
        }
    }
}
