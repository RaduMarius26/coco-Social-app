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

    @GetMapping("/login")
    public String loginScreen (Model model) {
        model.addAttribute("login", new User());
        return "login";
    }

    private @ResponseBody boolean checkUserLoginData (User login) {
        return (userRepository.checkUserPasswordCombination(login.getUserName(),login.getPassword()).size() == 1);
    }
    
    @PostMapping("/login")
    public String loginEvaluation(@ModelAttribute User login,Model model, HttpSession session) {
        if(checkUserLoginData(login)){
            session.setAttribute("userName", login.getUserName());
            return "loginSuccess";
        } else {
            model.addAttribute("login", new User());
            return "login";
        }
    }
}
