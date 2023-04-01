package com.example.studentapp.service;


import com.example.studentapp.model.TaskModel;
import com.example.studentapp.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
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

    public void updateTaskDescription(Long id, String description){
        repo.updateDescriptionById(id, description);
    }


    public List<TaskModel> findByColor(String color){
        List<TaskModel> colorList = repo.findByColor(color);
        log.info("obiekt koloru", colorList);
        return colorList;
    }





}
