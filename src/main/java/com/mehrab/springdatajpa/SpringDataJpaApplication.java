package com.mehrab.springdatajpa;

import com.github.javafaker.Faker;
import com.mehrab.springdatajpa.model.Student;
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
	CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
		return args -> {
			initFakeData(studentRepository);
			Sort sort = Sort.by("age").descending();
			PageRequest pageRequest = PageRequest.of(0, 5, sort);
			studentRepository.findAll(pageRequest).forEach(System.out::println);
		};
	}

	public void initFakeData(StudentRepository studentRepository) {
		List<Student> students = new ArrayList<>();
		Faker faker = new Faker();
		IntStream.range(0, 20).forEach((i) -> {
			String firstName = faker.name().firstName();
			String lastName = faker.name().lastName();
			String email = String.format("%s.%s@gmail.com", firstName, lastName);
			students.add(new Student(firstName, lastName, email, faker.number().numberBetween(17, 55)));
		});
		studentRepository.saveAll(students);
	}

}
