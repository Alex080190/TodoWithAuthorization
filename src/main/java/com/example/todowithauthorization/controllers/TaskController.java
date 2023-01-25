package com.example.todowithauthorization.controllers;

import com.example.todowithauthorization.models.Task;
import com.example.todowithauthorization.models.User;
import com.example.todowithauthorization.services.TaskService;
import com.example.todowithauthorization.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    private final UserService userService;

    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }
    @GetMapping("/add")
    public String getAddTaskPage(){
        return "/tasks/add";
    }
    @PostMapping("/add")
    public String addTask(@RequestParam("description") String description,
                        @RequestParam("completed") String completed) {
        Task task = new Task();
        task.setDescription(description);
        task.setCompleted(completed.equals("да"));
        task.setUserId(getUserId());
        taskService.save(task);
        return "redirect:/tasks/all";
    }
    @GetMapping("/all")
    public String showAllTasks(Model model) {
        model.addAttribute("tasks", taskService.findAllByUserId(getUserId()));
        return "/tasks/all";
    }
    private long getUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userService.findByName(authentication.getName());
        return user.get().getId();
    }
    @GetMapping("/edit")
    public String getEditPage() {
        return "tasks/edit";
    }
    @PostMapping("/edit")
    public String update(@RequestParam("id") long id,
                         @RequestParam("description") String description,
                         @RequestParam("completed") String completed) {
        Task task = taskService.findById(id).get();
        task.setDescription(description);
        task.setCompleted(completed.equals("да"));
        taskService.update(task);
        return "redirect:/tasks/all";
    }
    @GetMapping("/delete")
    public String GetDeletePage() {
        return "tasks/delete";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") long id) {
        Task task = taskService.findById(id).get();
        taskService.delete(task);
        return "redirect:/tasks/all";
    }


}
