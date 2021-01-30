package coco;

import coco.Models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;


@Controller
public class RegisterController {
  @Autowired
      private UserRepository userRepository;

  @Autowired
    private PostRepository postRepository;

  @GetMapping("/register")
  public String register(Model model) {
      model.addAttribute("searchUserName", new Information());
    model.addAttribute("register", new User());
      model.addAttribute("searchUserName", new Information());
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
      model.addAttribute("searchUserName", new Information());
    if(checkName(register)) {
        add(register);
          session.setAttribute("userName",register.getUserName());
      model.addAttribute("editPost", new Posts());
          model.addAttribute("profilePosts", postRepository.accountPosts((String) session.getAttribute("userName")));
          return "profile";
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
      postRepository.deleteAll();
      return "efectuat";
  }
  @GetMapping("/all")
  public @ResponseBody
  Iterable<Users> getAllUsers() {
    return userRepository.findAll();
  }
}