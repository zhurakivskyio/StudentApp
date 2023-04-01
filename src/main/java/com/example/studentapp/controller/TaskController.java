package com.example.studentapp.controller;

import com.example.studentapp.model.StudentModel;
import com.example.studentapp.model.TaskModel;
import com.example.studentapp.service.StudentService;
import com.example.studentapp.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final StudentService studentService;

    @GetMapping("/tasks")
    public String getTasksList(Model model) {
        List<TaskModel> list = taskService.getTasksList();
        model.addAttribute("taskModel", list);
        {
            return "tasks/tasks";
        }
    }

    @GetMapping("/addTask")
    public String getAddTask(Model model) {
        List<StudentModel> list = studentService.getStudentsList();
        model.addAttribute("studentModel", list);
        {
            return "tasks/addTask";
        }
    }

    @PostMapping("/addTask")
    public RedirectView postAddTask(TaskModel task) {
        taskService.addTask(task);
        return new RedirectView("/tasks");
    }

    @PatchMapping("/editTask/{id}")
    public RedirectView patchDescription(@PathVariable("id") Long id, @RequestParam String description) {
        taskService.updateTaskDescription(id, description);
        return new RedirectView("/tasks");
    }

    @GetMapping("/byColor/{color}")
    public RedirectView getByColor(@PathVariable String color) {
        taskService.findByColor(color);
        return new RedirectView("/tasks");
    }

}

