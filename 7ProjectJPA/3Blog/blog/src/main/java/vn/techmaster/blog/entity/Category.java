package vn.techmaster.blog.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@NamedNativeQuery(
        name = "getCategoriesPopular",
        query = "SELECT c.id, c.name\n" +
                "FROM category c \n" +
                "INNER JOIN\n" +
                "(\n" +
                "\tSELECT bc.categories_id as id , count(bc.blog_id) as count_blog\n" +
                "\tFROM blog_categories bc \n" +
                "\tGROUP BY bc.categories_id \n" +
                ") as temp\n" +
                "ON c.id = temp.id\n" +
                "ORDER BY temp.count_blog DESC\n" +
                "LIMIT ?1",
        resultClass = Category.class
)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Category category = (Category) o;
        return id != null && Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}