package coco;

import coco.Models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Controller
public class RegisterController {
  @Autowired
      private UserRepository userRepository;

  @GetMapping("/register")
  public String register(Model model) {
    model.addAttribute("register", new User());
    return "register";
  }

  private @ResponseBody void add (User register) {

      Users information = new Users();
      information.setPassword(register.getPassword());
      information.setUserName(register.getUserName());
      userRepository.save(information);

  }

  private @ResponseBody boolean checkName (User register) {
    if(userRepository.findUserName(register.getUserName()).size() == 0) {
      return true;
    } else {
      return false;
    }
  }

  @PostMapping ("/register")
  public String registerCheck(@ModelAttribute User register, Model model, HttpSession session) {
    if(checkName(register)) {
        add(register);
        model.addAttribute("register",register);
        session.setAttribute("userName",register.getUserName());
        return "registerSuccess";
    } else {
      model.addAttribute("badUsername", new String("USERNAME IN USE"));
      model.addAttribute("register", new User());
      return "register";
    }
  }

  @GetMapping("/nuke")
  public @ResponseBody
  String deleteAllPosts() {
      userRepository.deleteAll();
      return "efectuat";
  }
  @GetMapping("/all")
  public @ResponseBody
  Iterable<Users> getAllUsers() {
    return userRepository.findAll();
  }
}