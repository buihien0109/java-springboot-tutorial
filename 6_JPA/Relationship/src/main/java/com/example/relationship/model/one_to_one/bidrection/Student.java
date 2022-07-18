package com.example.relationship.model.one_to_one.bidrection;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private Card card;

    public void addCard(Card card) {
        this.setCard(card);
        card.setStudent(this);
    }

    public void removeCard(Card card) {
        this.setCard(null);
        getCard().setStudent(null);
    }

    public Student(String name) {
        this.name = name;
    }
}