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

import java.time.Instant;
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

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/add_post";
    }

    @PostMapping("/create")
    public String createPost(@ModelAttribute Post post, HttpSession session) {
        User loggedInUser = getLoggedInUser(session);
        post.setAuthor(loggedInUser);
        post.setCreatedAt(Instant.now());
        post.setUpdatedAt(null);
        post.setPublishedAt(Instant.now());
        postService.createPost(post);
        return "redirect:/posts";
    }

    // Detail post
    @GetMapping("/{id}")
    public String showPost(@PathVariable Long id, Model model) {
        Post post = postService.getPostById(id).orElse(null);
        if (post == null) {
            return "redirect:/posts";
        }

        User author = post.getAuthor();
        List<PostComment> comments = postService.getCommentsByPost(post);
        post.setAuthor(author);
        post.setComments(new HashSet<>(comments));

        model.addAttribute("post", post);
        return "posts/detail_post";
    }

    @GetMapping("/update/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Post post = postService.getPostById(id).orElse(null);
        if (post == null) {
            return "redirect:/posts";
        }

        model.addAttribute("post", post);
        return "posts/update_post";
    }

    @PostMapping("/update/{id}")
    public String editPost(@PathVariable Long id, @ModelAttribute Post post) {
        Post existingPost = postService.getPostById(id).orElse(null);
        if (existingPost == null) {
            return "redirect:/posts";
        }

        existingPost.setTitle(post.getTitle());
        existingPost.setMetaTitle(post.getMetaTitle());
        existingPost.setSummary(post.getSummary());
        existingPost.setContent(post.getContent());
        existingPost.setUpdatedAt(Instant.now());
        postService.updatePost(id, existingPost);
        return "redirect:/posts";
    }

    @PostMapping("/createComment/{id}")
    public String createComment(@PathVariable Long id, @RequestParam("content") String content, @RequestParam("title") String title, HttpSession session) {
        Post post = postService.getPostById(id).orElse(null);
        if (post == null) {
            return "redirect:/posts";
        }

        PostComment comment = new PostComment();
        comment.setPost(post);
        comment.setParent(null);
        comment.setPublished(true);
        comment.setTitle(title);
        comment.setContent(content);
        comment.setCreatedAt(Instant.now());
        comment.setPublishedAt(Instant.now());
        postService.saveComment(comment);
        return "posts/list_post";
    }
}