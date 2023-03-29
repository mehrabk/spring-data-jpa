package com.mehrab.springdatajpa.service;

import com.mehrab.springdatajpa.model.Course;
import com.mehrab.springdatajpa.payload.course.CourseRequest;

public interface CourseService {
    void init();
    Course add(CourseRequest courseRequest);
}
