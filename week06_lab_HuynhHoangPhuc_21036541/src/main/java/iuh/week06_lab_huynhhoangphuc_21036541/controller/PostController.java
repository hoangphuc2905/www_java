package iuh.week06_lab_huynhhoangphuc_21036541.controller;

import iuh.week06_lab_huynhhoangphuc_21036541.models.Post;
import iuh.week06_lab_huynhhoangphuc_21036541.models.PostComment;
import iuh.week06_lab_huynhhoangphuc_21036541.models.User;
import iuh.week06_lab_huynhhoangphuc_21036541.repositories.PostRepository;
import iuh.week06_lab_huynhhoangphuc_21036541.repositories.UserRepository;
import iuh.week06_lab_huynhhoangphuc_21036541.service.PostServices;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostServices postService;

    private User getLoggedInUser(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return null;
        }
        return userRepository.findById(userId).orElse(null);
    }

    @GetMapping("")
    public String listPosts(Model model, HttpSession session) {
        List<Post> posts = postRepository.findAll();
        User loggedInUser = getLoggedInUser(session);

        for (Post post : posts) {
            User author = post.getAuthor();
            List<PostComment> comments = postService.getCommentsByPost(post);
            post.setAuthor(author);
            post.setComments(new HashSet<>(comments));
        }

        model.addAttribute("posts", posts);
        model.addAttribute("loggedInUser", loggedInUser);
        return "posts/list_post";
    }


    @GetMapping("/user/{id}")
    public String listPostsByUser(@PathVariable Long id, Model model) {
        List<Post> posts = postService.getPostsByUser(id);

        for (Post post : posts) {
            User author = post.getAuthor();
            List<PostComment> comments = postService.getCommentsByPost(post);
            post.setAuthor(author);
            post.setComments(new HashSet<>(comments));
        }

        model.addAttribute("posts", posts);
        return "posts/list_post_user";
    }
}