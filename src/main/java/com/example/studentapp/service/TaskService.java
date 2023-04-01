package com.example.studentapp.service;


import com.example.studentapp.model.TaskModel;
import com.example.studentapp.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository repo;

    public void addTask(TaskModel task) {
        repo.save(task);
    }


    public List<TaskModel> getTasksList() {
        return repo.findAll();
    }


    public TaskModel getTaskById(Long id) {
        return repo.findById(id).orElse(null);
    }


    public void saveEditTask(TaskModel editTask) {
        repo.save(editTask);
    }

    public void removeTask(Long id) {
        repo.deleteById(id);
    }







}
