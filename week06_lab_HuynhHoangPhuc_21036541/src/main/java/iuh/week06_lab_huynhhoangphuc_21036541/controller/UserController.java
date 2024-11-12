package iuh.week06_lab_huynhhoangphuc_21036541.controller;

import iuh.week06_lab_huynhhoangphuc_21036541.models.User;
import iuh.week06_lab_huynhhoangphuc_21036541.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
        // Mã hóa mật khẩu trước khi lưu
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

    // Pass: password123
    @PostMapping("/login")
    public String login(@RequestParam("mobile") String mobile,
                        @RequestParam("password") String password, Model model) {
        List<User> users = userRepository.findAll();

        for (User u : users) {
            if (u.getMobile().equals(mobile) && u.getPasswordHash().equals(hashPasswordMD5(password))) {
                return "redirect:/users";
            }
        }

        model.addAttribute("error", "Invalid mobile number or password");
        return "users/login";
    }
}
