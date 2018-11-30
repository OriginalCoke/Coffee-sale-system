package com.hwy.proj_425.controller;

import com.hwy.proj_425.entities.User;
import com.hwy.proj_425.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class UserController {
    @Autowired
    public UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String listAllUsers(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "users";
    }

    @RequestMapping("user/{id}")
    public String getUserById(@PathVariable Integer id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "userDetail";
    }

    @RequestMapping("user/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "userForm";
    }

    @RequestMapping(value = "user", method = RequestMethod.POST)
    public String createUser(User user) {
        String pw = user.getPassword();
        user.setPassword(new BCryptPasswordEncoder().encode(pw));
        userService.createUser(user);
        return "redirect:/user/" + user.getId();
    }

    @RequestMapping("user/delete/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
