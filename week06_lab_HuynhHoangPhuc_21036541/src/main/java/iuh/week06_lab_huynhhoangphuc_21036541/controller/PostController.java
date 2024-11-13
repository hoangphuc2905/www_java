package iuh.week06_lab_huynhhoangphuc_21036541.controller;


import iuh.week06_lab_huynhhoangphuc_21036541.models.Post;
import iuh.week06_lab_huynhhoangphuc_21036541.models.PostComment;
import iuh.week06_lab_huynhhoangphuc_21036541.models.User;
import iuh.week06_lab_huynhhoangphuc_21036541.repositories.PostRepository;
import iuh.week06_lab_huynhhoangphuc_21036541.repositories.UserRepository;
import iuh.week06_lab_huynhhoangphuc_21036541.service.PostServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.xml.stream.events.Comment;
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

    @GetMapping("")
    public String listPosts(Model model) {
        List<Post> posts = postRepository.findAll();

        for (Post post : posts) {
            User author = post.getAuthor();

            List<PostComment> comments = postService.getCommentsByPost(post);

            post.setAuthor(author);
            post.setComments(new HashSet<>(comments));
        }

        model.addAttribute("posts", posts);

        return "posts/list_post";
    }
}