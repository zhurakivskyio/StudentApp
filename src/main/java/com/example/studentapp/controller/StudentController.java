package com.example.studentapp.controller;

import com.example.studentapp.model.StudentModel;
import com.example.studentapp.service.StudentService;
import com.example.studentapp.util.StudentException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.net.BindException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class StudentController {

    private final StudentService service;

    @GetMapping("/students")
    public String getStudentsList(Model model) {
        List<StudentModel> studentList = service.getStudentsList();
        model.addAttribute("studentModel", studentList);
        return "persons/personList";
    }

    @GetMapping("/addStudent")
    public String getAddStudent() {
        return "persons/addNewPerson";
    }

    @GetMapping("/editStudent/{id}")
    public String getEditStudent(@PathVariable("id") Long id, Model model) {
        StudentModel studentModel = service.getStudentById(id);
        model.addAttribute("studentModel", studentModel);
        return "persons/editPerson";
    }


    @PostMapping("/addStudent")
    public RedirectView postAddStudent(@Valid StudentModel student) {
        try {
            service.addStudent(student);
        } catch (StudentException e) {
            log.info("error: {}", e);
            return new RedirectView("/error");
        }
        return new RedirectView("/students");
    }


    @ExceptionHandler(BindException.class)
    public RedirectView handleBindException(BindException ex) {
        log.info("error: {}", ex);
        return new RedirectView("/error");
    }


    @PostMapping("/addStudent/{id}")
    public RedirectView postEditStudent(@PathVariable("id") Long id, StudentModel editStudent) {
        service.saveEditStudent(editStudent);
        return new RedirectView("/editStudent/{id}");
    }



    @PostMapping("/editStudent/{id}")
    public RedirectView removeStudent(@PathVariable("id") Long id){
        service.removeStudent(id);
        return new RedirectView("/students");
    }

    @GetMapping("/allStudents")
    public Page<StudentModel> getStudentsListPageable(Pageable pageable){
        int currentPage = pageable.getPageNumber();
        return service.getStudentsListPageable(pageable);
    }



}
