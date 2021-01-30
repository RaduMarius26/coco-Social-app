package coco;

import coco.Models.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;


@Controller
public class FeedControler {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    private @ResponseBody
    void add (Posts post) {
        postRepository.save(post);
    }


    @GetMapping("/createPost")
    public String mainPage (Model model) {
        model.addAttribute("post", new Posts());
        model.addAttribute("posts", postRepository.findAll());
        model.addAttribute("searchUserName", new Information());
        return "Feed";
    }

    @PostMapping("/searchUserName")
    public  String searchUser (@ModelAttribute Information searchUserName, Model model) {
        model.addAttribute("searchUserName", new Information());
        model.addAttribute("usersByName",userRepository.searchByUserName(searchUserName.getText()));
        return "usersFound";
    }


    @PostMapping("/createPost")
    public String addPost(@ModelAttribute Posts post, Model model, HttpSession session){
        post.setUserName((String) session.getAttribute("userName"));
        add(post);
        model.addAttribute("post",new Posts());
        model.addAttribute("posts", postRepository.findAll());
        model.addAttribute("searchUserName", new Information());
        return "Feed";
    }


    @GetMapping("/allPosts")
    public @ResponseBody
    Iterable<Posts> getAllPosts() {
        return postRepository.findAll();
    }

    @GetMapping("/clearAllPosts")
    public @ResponseBody
    String deleteAllPosts() {
        postRepository.deleteAll();
        return "efectuat";
    }
}
