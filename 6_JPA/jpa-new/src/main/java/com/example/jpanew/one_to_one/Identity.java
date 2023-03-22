package com.example.jpanew.one_to_one;

import jakarta.persistence.*;
import lombok.*;

@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "identity")
public class Identity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "code")
    private String code;

    @ToString.Exclude
    @OneToOne(mappedBy = "identity")
    private Developer developer;

    @PreRemove
    public void preRemove() {
        this.developer.setIdentity(null);
    }
}