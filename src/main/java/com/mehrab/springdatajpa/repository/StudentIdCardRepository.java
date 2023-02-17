package com.mehrab.springdatajpa.repository;

import com.mehrab.springdatajpa.model.StudentIdCard;
import org.springframework.data.repository.CrudRepository;

public interface StudentIdCardRepository extends CrudRepository<StudentIdCard, Long> {
}
