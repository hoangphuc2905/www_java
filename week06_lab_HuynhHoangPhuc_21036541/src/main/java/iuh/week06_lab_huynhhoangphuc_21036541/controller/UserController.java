package iuh.week06_lab_huynhhoangphuc_21036541.controller;

import iuh.week06_lab_huynhhoangphuc_21036541.models.User;
import iuh.week06_lab_huynhhoangphuc_21036541.repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("")
    public String listUsers(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "users/list_user";
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        user.setPasswordHash(hashPasswordMD5(user.getPasswordHash()));
        return userRepository.save(user);
    }

    private String hashPasswordMD5(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "users/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("mobile") String mobile,
                        @RequestParam("password") String password,
                        HttpSession session,
                        Model model) {
        List<User> users = userRepository.findAll();

        for (User u : users) {
            if (u.getMobile().equals(mobile) && u.getPasswordHash().equals(hashPasswordMD5(password))) {
                u.setLastLogin(Instant.now());
                userRepository.save(u);

                session.setAttribute("userId", u.getId());

                return "redirect:/posts";
            }
        }

        model.addAttribute("error", "Invalid mobile number or password");
        return "users/login";
    }


    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "users/register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        if (userRepository.existsByMobile(user.getMobile())) {
            model.addAttribute("error", "Mobile number already exists");
            return "users/register";
        }

        user.setRegisteredAt(Instant.now());
        user.setPasswordHash(hashPasswordMD5(user.getPasswordHash()));
        user.setLastLogin(null);
        userRepository.save(user);

        model.addAttribute("success", "Registration successful! Please log in.");

        return "redirect:/users/login";
    }

}
