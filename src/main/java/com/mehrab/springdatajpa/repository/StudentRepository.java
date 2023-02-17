package com.mehrab.springdatajpa.repository;

import com.mehrab.springdatajpa.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> getStudentsByEmail(String email);
    List<Student> findStudentsByFirstNameEqualsAndAgeEquals(String firstName, Integer age);
}
