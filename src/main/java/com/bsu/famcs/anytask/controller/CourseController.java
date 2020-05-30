package com.bsu.famcs.anytask.controller;

import com.bsu.famcs.anytask.entity.*;
import com.bsu.famcs.anytask.service.interfaces.CourseService;
import com.bsu.famcs.anytask.service.interfaces.StudentTaskStatusService;
import com.bsu.famcs.anytask.service.interfaces.UserService;
import com.bsu.famcs.anytask.validator.CourseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseService;
    private final UserService userService;
    private final CourseValidator courseValidator;
    private final StudentTaskStatusService studentTaskStatusService;

    @Autowired
    public CourseController(CourseService courseService, UserService userService,
                            CourseValidator courseValidator, StudentTaskStatusService studentTaskStatusService) {
        this.courseService = courseService;
        this.userService = userService;
        this.courseValidator = courseValidator;
        this.studentTaskStatusService = studentTaskStatusService;
    }

    @GetMapping("/courseCreate")
    public String courseAdd(Model model) {
        model.addAttribute("course", new Course());
        return "courseCreate";
    }

    @PostMapping("/courseCreate")
    public String courseAdd(@ModelAttribute("course") Course course, BindingResult bindingResult) {
        courseValidator.validate(course, bindingResult);
        if (bindingResult.hasErrors()) {
            return "courseCreate";
        }

        User user = getCurrentUser();

        course.setTeacher(user);
        courseService.create(course);
        return "redirect:/start";
    }

    @GetMapping("{course}")
    public String courseShow(@PathVariable Course course, Model model) {
        User user = getCurrentUser();

        Set<StudentTaskStatus> taskStatuses = new HashSet<>();
        for (Task task : course.getTaskSet()) {
            for (StudentTaskStatus studentTaskStatus : user.getStudentTaskStatusSet()) {
                if (task.equals(studentTaskStatus.getTask())) {
                    taskStatuses.add(studentTaskStatus);
                }
            }
        }

        List<StudentTaskStatus> inProgressTasks = new ArrayList<>();
        for (User userLoop : course.getStudentSet()) {
            for (Task task : course.getTaskSet()) {
                inProgressTasks.add(studentTaskStatusService.findStudentTaskStatusByLabelAndTaskAndStudent(Label.IN_PROGRESS,
                        task, userLoop));
            }
        }
        List<StudentTaskStatus> readyForReviewTasks = new ArrayList<>();
        for (User userLoop : course.getStudentSet()) {
            for (Task task : course.getTaskSet()) {
                readyForReviewTasks.add(studentTaskStatusService.findStudentTaskStatusByLabelAndTaskAndStudent(
                        Label.READY_FOR_REVIEW, task, userLoop));
            }
        }

        if (course.getTeacher().equals(user)) {
            model.addAttribute("course", course);
            model.addAttribute("taskList", course.getTaskSet());
            model.addAttribute("studentList", course.getStudentSet());
            model.addAttribute("inProgressTasks", inProgressTasks);
            model.addAttribute("readyForReviewTasks", readyForReviewTasks);
            return "teacherCoursePage";
        } else if (course.getStudentSet().contains(user)) {
            model.addAttribute("course", course);
            model.addAttribute("user", user);
            model.addAttribute("taskStatusList", taskStatuses);
            return "studentCoursePage";
        } else {
            model.addAttribute("course", course);
            return "course";
        }
    }

    @GetMapping("/{course}/join")
    public String courseJoin(@PathVariable Course course) {
        User user = getCurrentUser();

        Set<User> students = course.getStudentSet();
        students.add(user);
        course.setStudentSet(students);

        Set<StudentTaskStatus> userTasks = user.getStudentTaskStatusSet();

        for (Task task : course.getTaskSet()) {
            StudentTaskStatus studentTaskStatus = new StudentTaskStatus();
            studentTaskStatus.setTask(task);
            studentTaskStatus.setLabel(Label.NEW);
            studentTaskStatus.setMark(0);

            userTasks.add(studentTaskStatus);
        }

        user.setStudentTaskStatusSet(userTasks);
        userTasks.forEach(x -> x.setStudent(user));

        userService.update(user);
        return "redirect:/course/{course}";
    }

    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return userService.findByUsername(username);
    }
}
