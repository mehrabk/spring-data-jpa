package com.mehrab.springdatajpa.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Student")
@Table(
        name = "student",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "student_email_uk", columnNames = "email"
                )
        }
)
public class Student {
    @Id
    @SequenceGenerator(name = "student_sequence", sequenceName = "student_sequence", allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )

    @Column(updatable = false)
    private Long id;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String firstName;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String lastName;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String email;
    @Column(nullable = false)
    private Integer age;

    // orphanRemoval -> when delete student then also delete studentIdCard
    @OneToOne(mappedBy = "student", orphanRemoval = true, cascade = CascadeType.ALL) // create bi-directional relationship (sql -> left outer join with student_id_card...)
    private StudentIdCard studentIdCard;

    @OneToMany(mappedBy = "student", orphanRemoval = true, cascade = CascadeType.ALL) // create bi-directional relationshi
    private List<Book> books = new ArrayList<>();


//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
//    @JoinTable(
//            name = "enrolment",
//            joinColumns = @JoinColumn(
//                    name = "student_id",
//                    referencedColumnName = "id",
//                    foreignKey = @ForeignKey(name = "enrolment_student_id_fk")
//            ),
//            inverseJoinColumns = @JoinColumn(
//                    name = "course_id",
//                    referencedColumnName = "id",
//                    foreignKey = @ForeignKey(name = "enrolment_course_id_fk")
//            )
//    )
//    private List<Course> courses = new ArrayList<>();

    // we create manually ManyToMany
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "student")
    private List<Enrolment> enrolments = new ArrayList<>();

    public Student() {
    }

    public Student(String firstName, String lastName, String email, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setStudentIdCard(StudentIdCard studentIdCard) {
        this.studentIdCard = studentIdCard;
    }

    public void addBook(Book book) {
        if(!books.contains(book)) {
            books.add(book);
            book.setStudent(this);
        }
    }

    public void removeBook(Book book) {
        if(this.books.contains(book)) {
            this.books.remove(book);
            book.setStudent(null);
        }
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Enrolment> getEnrolments() {
        return enrolments;
    }

    public void addEnrolment(Enrolment enrolment) {
        if(!enrolments.contains(enrolment)) {
            enrolments.add(enrolment);
        }
    }

    public void removeEnrolment(Enrolment enrolment) {
        if(enrolments.contains(enrolment)) {
            enrolments.remove(enrolment);
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
// student_id_card & book -> eager fetch then:
//  student left join student_id_card and book tables