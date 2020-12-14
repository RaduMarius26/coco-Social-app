package coco.aplication;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

  @GetMapping("/register")
  public String registerIN(Model model) {
    model.addAttribute("register", new User());
    return "registerIN";
  }

  @PostMapping("/register")
  public String registerOUT(@ModelAttribute User register, Model model) {
    model.addAttribute("register", register);
    return "registerOUT";
  }

}