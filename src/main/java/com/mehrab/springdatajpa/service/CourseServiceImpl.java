package com.mehrab.springdatajpa.service;

import com.mehrab.springdatajpa.exception.NotFoundException;
import com.mehrab.springdatajpa.model.Course;
import com.mehrab.springdatajpa.payload.course.CourseRequest;
import com.mehrab.springdatajpa.repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.stream.IntStream;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    // without @Transaction 7 record will insert to db.
    // spring using AOP (reflection & proxy) handle transaction
    // @Transactional cant automatic rollback if throw exception inside try-catch block(fix: need propagation exception or direct throw exception)
    // @Transactional for default doesn't rollback for checked exceptions.
    // rollbackOn = Exception.class -> rollback for all checked & unchecked exceptions
    // propagation is default required -> always start new transaction incase a transaction doesnt exist. if it exists it use the same one.
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public void init() {
        try {
            IntStream.range(1, 10).forEach((i) -> {
                courseRepository.save(new Course("Course-" + i, "Department-" + i ));
                System.out.println("Course Created");
//                if(i == 7) {
//                    throw new NotFoundException("simulate runtime exception");
//                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw e;
        }

    }

    @Override
    public Course add(CourseRequest courseRequest) {
        return null;
    }
}
