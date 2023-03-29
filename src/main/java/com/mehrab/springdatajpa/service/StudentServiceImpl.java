package com.mehrab.springdatajpa.service;

import com.mehrab.springdatajpa.exception.NotFoundException;
import com.mehrab.springdatajpa.model.Student;
import com.mehrab.springdatajpa.payload.student.StudentRequest;
import com.mehrab.springdatajpa.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student save(StudentRequest req) {
        Long id = req.id();
        Student student = id > 0
                ? studentRepository.findById(id).orElseThrow(() -> new NotFoundException("Student With Id " + id + "not found!"))
                : new Student();

        student.setFirstName(req.firstName());
        student.setLastName(req.lastName());
        student.setAge(req.age());
        student.setEmail(req.email());

        Student newStudent = studentRepository.save(student);

        return newStudent;
    }
}
