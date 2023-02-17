package com.mehrab.springdatajpa.repository;

import com.mehrab.springdatajpa.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> getStudentsByEmail(String email);

    List<Student> findStudentsByFirstNameEqualsAndAgeEquals(String firstName, Integer age);

    @Query("SELECT s FROM Student s WHERE s.email = ?1") // JPQL
        // is good to use JPQL because in future if we want change database to mysql this will work (not specific for database)
    Optional<Student> getStudentByEmailAddress(String email);

    @Query(value = "select s.* from student s where s.first_name = :firstName AND s.age >= :age", nativeQuery = true) // native for postgresql
    Optional<Student> getStudentFromNativQuery(@Param("firstName") String firstName, @Param("age") Integer age);

    @Transactional // DML statemet and create session
    @Modifying // tell to spring data to we doest need to map data from database to entity
    @Query("DELETE FROM Student s WHERE s.id = ?1")
    int deleteStudentByID(Long id);
}
