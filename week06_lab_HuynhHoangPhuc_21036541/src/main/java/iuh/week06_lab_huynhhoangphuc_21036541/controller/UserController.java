package iuh.week06_lab_huynhhoangphuc_21036541.controller;

import iuh.week06_lab_huynhhoangphuc_21036541.models.User;
import iuh.week06_lab_huynhhoangphuc_21036541.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public String listUsers(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "users/list_user";
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }


    // Login
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        List<User> users = userRepository.findAll();
        for (User u : users) {
            if (u.getMobile().equals(user.getMobile()) && u.getPasswordHash().equals(user.getPasswordHash())) {
                return "users/login";
            }
        }
        return "users/login";
    }
}

