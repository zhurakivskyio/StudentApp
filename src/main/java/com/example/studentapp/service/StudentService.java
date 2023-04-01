package com.example.studentapp.service;

import com.example.studentapp.model.StudentModel;
import com.example.studentapp.repository.StudentRepository;
import com.example.studentapp.util.StudentException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentService {

    private final StudentRepository repo;


    public void addStudent(StudentModel student) throws StudentException {
        try {
            repo.save(student);
        } catch (Exception e) {
            throw new StudentException("Nie udało się dodać studenta", e);
        }
    }


    public List<StudentModel> getStudentsList() {
        return repo.findAll();
    }


    public StudentModel getStudentById(Long id) {
        return repo.findById(id).orElse(null);
    }


    public void saveEditStudent(StudentModel editStudent) {
        repo.save(editStudent);
    }

    public void removeStudent(Long id) {
        repo.deleteById(id);
    }
}
