package com.mehrab.springdatajpa.service;

import com.mehrab.springdatajpa.model.Student;
import com.mehrab.springdatajpa.payload.student.StudentRequest;

public interface StudentService {
    Student save(StudentRequest studentRequest);
}
