package com.bsu.famcs.anytask.controller;

import com.bsu.famcs.anytask.entity.Course;
import com.bsu.famcs.anytask.entity.Role;
import com.bsu.famcs.anytask.entity.User;
import com.bsu.famcs.anytask.service.interfaces.CourseService;
import com.bsu.famcs.anytask.service.interfaces.UserService;
import com.bsu.famcs.anytask.validator.UserValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;
    private final UserValidator userValidator;
    private final CourseService courseService;

    public UserController(UserService userService, UserValidator userValidator, CourseService courseService) {
        this.userService = userService;
        this.userValidator = userValidator;
        this.courseService = courseService;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("teacher", Role.TEACHER);
        model.addAttribute("student", Role.STUDENT);
        return "signUp";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "signUp";
        }

        user.setActive(true);
        userService.save(user);
        return "redirect:/start";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        //user.setActive(true);
        if (error != null) {
            model.addAttribute("error", "Your username and password is invalid.");
        }

        if (logout != null) {
         //   User user = g
            //user.setActive(false);
            model.addAttribute("message", "You have been logged out successfully.");
        }

        return "signIn";
    }


    @GetMapping({"/", "/start"})
    public String getAllCourses(Model model) {
        List<Course> courseList = courseService.findAll();
        model.addAttribute("courseList", courseList);
        return "main";
    }
}
