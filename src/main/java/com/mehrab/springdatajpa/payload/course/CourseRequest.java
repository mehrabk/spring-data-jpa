package com.mehrab.springdatajpa.payload.course;

import jakarta.validation.constraints.NotNull;

public record CourseRequest(
        @NotNull Long id,
        @NotNull String name,
        @NotNull String department
) {}
