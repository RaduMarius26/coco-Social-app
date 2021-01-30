package coco;

import coco.Models.Information;
import coco.Models.PostRepository;
import coco.Models.Posts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ProfileControler {
    @Autowired
    private PostRepository postRepository;

    @GetMapping("/profile")
    public String getProfile(HttpSession session, Model model) {
        model.addAttribute("searchUserName", new Information());
        model.addAttribute("editPost", new Posts());
        model.addAttribute("name",session.getAttribute("userName"));
        model.addAttribute("profilePosts", postRepository.accountPosts((String) session.getAttribute("userName")));
        return "profile";
    }
    @GetMapping("/profileSearch/{userName}")
    public  String searchProfile(@PathVariable ("userName") String userName, Model model) {
        model.addAttribute("searchUserName", new Information());
        model.addAttribute("editPost", new Posts());
        model.addAttribute("name",userName);
        model.addAttribute("profilePosts", postRepository.accountPosts(userName));
        return  "searchProfile";
    }

    @PutMapping ("/editPost")
    public String editPost(Model model) {
        model.addAttribute("searchUserName", new Information());
        model.addAttribute("editPost", new Posts());
        return "profile";
    }
}
