package com.mehrab.springdatajpa.payload.student;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

public record StudentRequest (
        @NotNull Long id,
        @NotNull @Length(min = 4) String firstName,
        @NotNull @Length(min = 4) String lastName,
        @NotNull @Email String email,
        @NotNull @Size(min = 10) Integer age
){}
