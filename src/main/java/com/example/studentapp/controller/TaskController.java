package com.example.studentapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TaskController {

    @GetMapping("/tasks")
    public String getTasksList() {
        return "tasks/tasks";
    }

    @GetMapping("/addTask")
    public String getAddTask() {
        return "tasks/addTask";
    }



}
