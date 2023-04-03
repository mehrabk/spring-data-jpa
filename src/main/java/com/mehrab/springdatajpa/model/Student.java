package com.mehrab.springdatajpa.model;

import com.mehrab.springdatajpa.model.user.User;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(

)
@DiscriminatorValue("student")
public class Student extends User {

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
        super(firstName, lastName, email, age);
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
}
// student_id_card & book -> eager fetch then:
//  student left join student_id_card and book tables