package com.mehrab.springdatajpa.model;

import jakarta.persistence.*;

@Entity(name = "StudentIdCard")
@Table(name = "student_id_card",
        uniqueConstraints = {
        @UniqueConstraint(
                name = "student_id_card_number_uk", columnNames = "card_number"
        )
})
public class StudentIdCard {

    @Id
    @SequenceGenerator(name = "student_card_id_sequence", sequenceName = "student_card_id_sequence", allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_card_id_sequence"
    )

    @Column(updatable = false)
    private Long id;

    @Column(name = "card_number", nullable = false, length = 15)
    private String cardNumber;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER) //Owning Entity (have FK of student)
    @JoinColumn(name = "student_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "student_id_fk"))
    private Student student;

    public StudentIdCard() {
    }

    public StudentIdCard(String cardNumber, Student student) {
        this.cardNumber = cardNumber;
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
