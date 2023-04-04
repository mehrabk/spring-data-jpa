package com.mehrab.springdatajpa;

import com.github.javafaker.Faker;
import com.mehrab.springdatajpa.model.*;
import com.mehrab.springdatajpa.repository.StudentIdCardRepository;
import com.mehrab.springdatajpa.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootApplication
@ComponentScan("com.mehrab")
public class SpringDataJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
		return args -> {
			Faker faker = new Faker();

			String firstName = faker.name().firstName();
			String lastName = faker.name().lastName();
			String email = String.format("%s.%s@gmail.com", firstName, lastName);
			Student student = new Student(
					firstName,
					lastName,
					email,
					faker.number().numberBetween(17, 55));

			Book b1 = new Book("Clean Code", LocalDateTime.now().minusDays(4));
			student.addBook(b1);
			Book b2 = new Book("Spring Data JPA", LocalDateTime.now().minusYears(1));
			student.addBook(b2);
			Book b3 = new Book("Think and Grow Rich", LocalDateTime.now());
			student.addBook(b3);

			StudentIdCard studentIdCard = new StudentIdCard(
					"123456789",
					student);

			student.setStudentIdCard(studentIdCard);

			Course c1 = new Course("Computer Science", "IT");
			Course c2 = new Course("Big Data", "AI");

			student.addEnrolment(new Enrolment(
					new EnrolmentId(student.getId(), c1.getId()),
					student,
					c1,
					LocalDateTime.now()
			));

			student.addEnrolment(new Enrolment(
					new EnrolmentId(student.getId(), c2.getId()),
					student,
					c2,
					LocalDateTime.now().minusDays(18)
			));

			studentRepository.save(student);

			studentRepository.findById(1L)
					.ifPresent(s -> {
						System.out.println("fetch book lazy...");
						List<Book> books = student.getBooks();
						books.forEach(book -> {
							System.out.println(
									s.getFirstName() + " borrowed " + book.getBookName());
						});
					});
		};
	}

}
