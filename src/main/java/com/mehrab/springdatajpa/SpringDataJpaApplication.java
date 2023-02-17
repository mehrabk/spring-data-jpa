package com.mehrab.springdatajpa;

import com.github.javafaker.Faker;
import com.mehrab.springdatajpa.model.Student;
import com.mehrab.springdatajpa.model.StudentIdCard;
import com.mehrab.springdatajpa.repository.StudentIdCardRepository;
import com.mehrab.springdatajpa.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootApplication
public class SpringDataJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository, StudentIdCardRepository studentIdCardRepository) {
		return args -> {
			Faker faker = new Faker();
			String firstName = faker.name().firstName();
			String lastName = faker.name().lastName();
			String email = String.format("%s.%s@gmail.com", firstName, lastName);
			Student st = new Student(firstName, lastName, email, faker.number().numberBetween(17, 55));
			studentIdCardRepository.save(new StudentIdCard("1234567890", st));
		};
	}

}
