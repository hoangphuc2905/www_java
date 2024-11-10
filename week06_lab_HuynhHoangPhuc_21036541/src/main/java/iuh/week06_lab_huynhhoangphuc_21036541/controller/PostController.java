package iuh.week06_lab_huynhhoangphuc_21036541.controller;


import iuh.week06_lab_huynhhoangphuc_21036541.models.Post;
import iuh.week06_lab_huynhhoangphuc_21036541.repositories.PostRepository;
import iuh.week06_lab_huynhhoangphuc_21036541.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public String listPosts(Model model) {
        List<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "posts/list_post";
    }

}

