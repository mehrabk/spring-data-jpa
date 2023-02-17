package com.mehrab.springdatajpa;

import com.mehrab.springdatajpa.model.Student;
import com.mehrab.springdatajpa.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringDataJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				studentRepository.save(new Student("mehrab", "kor", "mehrab.kor@gmail.com", 29));
				studentRepository.save(new Student("mehrab2", "kor2", "mehrab.kor2@gmail.com", 29));
				studentRepository.getStudentsByEmail("mehrab.kor@gmail.com").ifPresentOrElse(System.out::println, () -> System.out.println("mehrab not found"));
				studentRepository.findStudentsByFirstNameEqualsAndAgeEquals("mehrab", 29).forEach(System.out::println);
				studentRepository.getStudentByEmailAddress("mehrab.kor2@gmail.com").ifPresentOrElse(System.out::println, () -> System.out.println("mehrab2 not found"));
				studentRepository.getStudentFromNativQuery("mehrab2", 20).ifPresentOrElse(System.out::println, () -> System.out.println("mehrab2 not found"));
				System.out.println(studentRepository.deleteStudentByID(2L));

			}
		};
	}

}
