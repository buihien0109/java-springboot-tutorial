package com.example.relationship.model.one_to_many.bidrection;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items = new ArrayList<>();

    void addItem(Item item) {
        this.items.add(item);
        item.setOrder(this);
    }

    void removeItem(Item item) {
        this.items.remove(item);
        item.setOrder(null);
    }
}