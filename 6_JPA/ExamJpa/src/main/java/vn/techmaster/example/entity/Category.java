package vn.techmaster.example.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = false)
    private Set<Product> products = new LinkedHashSet<>();

    public void addProduct(Product product) {
        this.products.add(product);
        product.setCategory(this);
    }

    public void removeProduct(Product product) {
        product.setCategory(null);
        this.products.remove(product);
    }

    public void removeProducts() {
        Iterator<Product> iterator = this.products.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            product.setCategory(null);
            iterator.remove();
        }
    }

    public Category(String name) {
        this.name = name;
    }

    // Trường hợp muốn xóa category mà không xóa product
    // set orphanRemoval = false
    // setNull cho tất cả product
    // clear Set product
    @PreRemove
    public void preRemove() {
        this.products.forEach(product -> product.setCategory(null));
        this.products.clear();
    }
}