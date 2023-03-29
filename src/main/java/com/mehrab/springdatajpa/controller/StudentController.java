package com.mehrab.springdatajpa.controller;

import com.mehrab.springdatajpa.model.Student;
import com.mehrab.springdatajpa.payload.student.StudentRequest;
import com.mehrab.springdatajpa.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(path = "/add")
    public ResponseEntity<Student> save(@RequestBody StudentRequest studentRequest) {
        return ResponseEntity.ok(studentService.save(studentRequest));
    }
}
