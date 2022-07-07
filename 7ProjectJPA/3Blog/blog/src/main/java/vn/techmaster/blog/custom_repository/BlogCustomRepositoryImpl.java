package vn.techmaster.blog.custom_repository;


import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Component;
import vn.techmaster.blog.dto.BlogDto;

import javax.persistence.*;
import java.util.List;

@Component
public class BlogCustomRepositoryImpl implements BlogCustomRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<BlogDto> getListBlogDto() {
        Session session = em.unwrap(Session.class);

        String queryString = "select id, content, description from blog";
        NativeQuery namedQuery = session.createSQLQuery(queryString);
        Query query = em.createNativeQuery(queryString);

        return null;
    }
}
