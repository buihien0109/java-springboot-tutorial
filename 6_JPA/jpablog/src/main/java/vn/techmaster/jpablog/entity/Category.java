package vn.techmaster.jpablog.entity;

import lombok.*;
import org.hibernate.Hibernate;
import vn.techmaster.jpablog.model.dto.CategoryInfo;

import javax.persistence.*;
import java.util.Objects;

@SqlResultSetMapping(
        name = "categoryInfo",
        classes = @ConstructorResult(
                targetClass = CategoryInfo.class,
                columns = {
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "blog_count", type = Integer.class),
                }
        )
)
@NamedNativeQuery(
        name = "getCategoryNameAndBlogCountt",
        resultSetMapping = "categoryInfo",
        query = "SELECT c.name, COUNT(bc.blog_id) as blog_count " +
                "FROM category c " +
                "LEFT JOIN blog_categories bc " +
                "ON c.id = bc.categories_id " +
                "GROUP BY c.id"
)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
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