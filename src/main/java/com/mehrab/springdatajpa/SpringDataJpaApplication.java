package com.mehrab.springdatajpa;

import com.github.javafaker.Faker;
import com.mehrab.springdatajpa.model.Book;
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

import java.time.LocalDateTime;
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

			st.addBook(new Book(LocalDateTime.now().minusDays(4),"Clean Code"));
			st.addBook(new Book(LocalDateTime.now(),"Gang Of Four"));
			st.addBook(new Book(LocalDateTime.now().minusYears(1),"Spring Data JPA"));

			StudentIdCard sic = new StudentIdCard("1234567890", st);
			st.setStudentIdCard(sic);

			studentRepository.save(st);

			studentRepository.findById(1L);
		};
	}

}
