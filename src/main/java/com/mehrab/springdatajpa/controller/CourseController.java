package com.mehrab.springdatajpa.controller;

import com.mehrab.springdatajpa.service.CourseService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/course")
@CrossOrigin(origins = "*")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // for transaction test
    @GetMapping(path = "/init")
    public String init() {
        System.out.println("saa");
        courseService.init();
        return "inintlized";
    }
}
