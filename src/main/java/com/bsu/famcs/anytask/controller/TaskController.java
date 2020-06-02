package com.bsu.famcs.anytask.controller;

import com.bsu.famcs.anytask.entity.*;
import com.bsu.famcs.anytask.service.interfaces.CloudinaryService;
import com.bsu.famcs.anytask.service.interfaces.StudentTaskStatusService;
import com.bsu.famcs.anytask.service.interfaces.TaskService;
import com.bsu.famcs.anytask.service.interfaces.UserService;
import com.bsu.famcs.anytask.validator.StudentTaskStatusValidator;
import com.bsu.famcs.anytask.validator.TaskValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Controller
@RequestMapping("/{course}/task")
public class TaskController {

    private final TaskService taskService;
    private final TaskValidator taskValidator;
    private final UserService userService;
    private final CloudinaryService cloudinaryService;
    private final StudentTaskStatusService studentTaskStatusService;

    @Autowired
    public TaskController(TaskService taskService, TaskValidator taskValidator, UserService userService,
                          CloudinaryService cloudinaryService, StudentTaskStatusService studentTaskStatusService) {
        this.taskService = taskService;
        this.taskValidator = taskValidator;
        this.userService = userService;
        this.cloudinaryService = cloudinaryService;
        this.studentTaskStatusService = studentTaskStatusService;
    }

    @PreAuthorize("hasAuthority('TEACHER')")
    @GetMapping("/taskAdd")
    public String taskAdd(@PathVariable Course course, Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("course", course);
        return "taskAdd";
    }

    @PreAuthorize("hasAuthority('TEACHER')")
    @PostMapping("/taskAdd")
    public String taskAdd(@PathVariable Course course, @ModelAttribute("task") Task task, BindingResult bindingResult) {
        taskValidator.validate(task, bindingResult);
        if (bindingResult.hasErrors()) {
            return "taskAdd";
        }

        Set<Task> tasks = course.getTaskSet();
        tasks.add(task);
        course.setTaskSet(tasks);
        task.setCourse(course);
        taskService.create(task);

        Set<StudentTaskStatus> statuses;
        StudentTaskStatus studentTaskStatus;
        for (User user : course.getStudentSet()) {
            statuses = user.getStudentTaskStatusSet();

            studentTaskStatus = new StudentTaskStatus();
            studentTaskStatus.setLabel(Label.NEW);
            studentTaskStatus.setMark(0);
            studentTaskStatus.setTask(task);

            statuses.add(studentTaskStatus);
            user.setStudentTaskStatusSet(statuses);
            for (StudentTaskStatus studentTaskStatusLoop : user.getStudentTaskStatusSet()) {
                if (studentTaskStatusLoop.getTask().getId() == studentTaskStatus.getTask().getId()) {
                    studentTaskStatusLoop.setStudent(user);
                }
            }
        }
        return "redirect:/course/{course}";
    }

    @PreAuthorize("hasAuthority('TEACHER')")
    @GetMapping("/{task}/delete")
    public String deleteTask(@PathVariable Task task) {
        taskService.delete(task);
        return "redirect:/course/{course}";
    }

    @PreAuthorize("hasAuthority('TEACHER')")
    @GetMapping("{task}/edit")
    public String taskEdit(@PathVariable Task task, Model model) {
        model.addAttribute("task", task);
        return "taskEdit";
    }

    @PreAuthorize("hasAuthority('TEACHER')")
    @PostMapping("{task}/edit")
    public String taskEdit(@PathVariable Course course, @PathVariable Task task, @ModelAttribute("task") Task modelTask, BindingResult bindingResult) {
        taskValidator.validate(task, bindingResult);
        if (bindingResult.hasErrors()) {
            return "taskEdit";
        }

        task.setName(task.getName());
        task.setCourse(course);
        taskService.save(task);
        return "redirect:/course/{course}";
    }

    @PreAuthorize("hasAuthority('STUDENT')")
    @GetMapping("{task}/setStartDate")
    public String setStartDate(@PathVariable Task task) {
        User user = getCurrentUser();
        String currentDate = getCurrentDate();
        StudentTaskStatus studentTaskStatus = new StudentTaskStatus();
        studentTaskStatus.setTask(task);

        for (StudentTaskStatus i : user.getStudentTaskStatusSet()) {
            if (i.getTask().getId() == studentTaskStatus.getTask().getId()) {
                i.setStartDate(currentDate);
                i.setLabel(Label.IN_PROGRESS);
            }
        }
        taskService.save(task);
        return "redirect:/course/{course}";
    }

    //or edit with uploading
    @GetMapping("{task}/setEndDate")
    public String setEndDate(@PathVariable Task task) {
        User user = getCurrentUser();
        String currentDate = getCurrentDate();
        StudentTaskStatus studentTaskStatus = new StudentTaskStatus();
        studentTaskStatus.setTask(task);

        for (StudentTaskStatus i : user.getStudentTaskStatusSet()) {
            if (i.getTask().getId() == studentTaskStatus.getTask().getId()) {
                i.setEndDate(currentDate);
                i.setLabel(Label.READY_FOR_REVIEW);
            }
        }
        taskService.save(task);
        return "redirect:/course/{course}";
    }

    @GetMapping("/{task}/upload")
    public String uploadForm(@PathVariable String task) {
        return "studentCoursePage";
    }

    @PostMapping("/{task}/upload")
    public String uploadFile(@PathVariable Task task, @RequestParam("file") MultipartFile file, Model model) {
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a file to upload");
            return "studentCoursePage";
        }

        User user = getCurrentUser();
        String url = cloudinaryService.uploadFile(file);

        StudentTaskStatus studentTaskStatus = new StudentTaskStatus();
        studentTaskStatus.setTask(task);

        for (StudentTaskStatus i : user.getStudentTaskStatusSet()) {
            if (i.getTask().getId() == studentTaskStatus.getTask().getId()) {
                i.setUrl(url);
                i.setLabel(Label.READY_FOR_REVIEW);
                i.setEndDate(getCurrentDate());
            }
        }

        taskService.save(task);
        return "redirect:/course/{course}";
    }

    @PreAuthorize("hasAuthority('TEACHER')")
    @GetMapping("{task}")
    public String taskShow(@PathVariable Task task, Model model) {
        model.addAttribute("task", task);
        model.addAttribute("studentList", task.getCourse().getStudentSet());
        return "teacherTaskShow";
    }

    @PreAuthorize("hasAuthority('TEACHER')")
    @GetMapping("/{task}/setReopen")
    public String setLabelReopenForTask(@PathVariable Task task, @PathVariable Course course) {
        task.getStudentTaskStatus().setLabel(Label.REOPEN);
        task.getStudentTaskStatus().setMark(0);
        taskService.save(task);
        return "redirect:/{course}/task/{task}";
    }

    @PreAuthorize("hasAuthority('TEACHER')")
    @RequestMapping(params = {"_csrf", "mark", "user"}, value = "/{task}/check", method = RequestMethod.POST)
    public String taskCheck(@PathVariable Task task,
                            @RequestParam("user") String username,
                            @RequestParam("_csrf") String _csrf,
                            @RequestParam("mark") String mark,
                            @ModelAttribute("taskStatus") StudentTaskStatus studentTaskStatus,
                            BindingResult bindingResult) {

//        studentTaskStatusValidator.validate(studentTaskStatus, bindingResult);
//        if (bindingResult.hasErrors()) {
//            return "redirect:/{course}/task/{task}";
//        }

        int studentMark = Integer.parseInt(mark);
        User user = userService.findByUsername(username);

        for (StudentTaskStatus i : user.getStudentTaskStatusSet()) {
            if (i.getTask().getId() == studentTaskStatus.getTask().getId()) {
                i.setStudent(i.getStudent());
                i.setTask(i.getTask());
                i.setUrl(i.getUrl());
                i.setLabel(Label.DONE);
                i.setMark(studentMark);
                studentTaskStatusService.update(i);
            }
        }
        return "redirect:/{course}/task/{task}";
    }

    private String getCurrentDate() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return userService.findByUsername(username);
    }
}
