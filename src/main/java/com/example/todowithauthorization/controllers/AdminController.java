package com.example.todowithauthorization.controllers;

import com.example.todowithauthorization.models.User;
import com.example.todowithauthorization.services.TaskService;
import com.example.todowithauthorization.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final TaskService taskService;
    private final UserService userService;
    public AdminController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }
    @GetMapping("/hello")
    public String hello() {
        return "admin/hello";
    }
    @GetMapping("/users")
    public String showAllUsers(Model model){
        model.addAttribute("users", userService.findAll());
        return "admin/users";
    }
    @GetMapping("/users/delete")
    public String getDeleteUserPage(){
        return "admin/delete";
    }
    @PostMapping("/users/delete")
    public String deleteUser(@RequestParam("id") long id){
        User user =  userService.findById(id).get();
        userService.delete(user);
        return "redirect:/admin/users";
    }

}
