package vn.techmaster.entitytodto.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.techmaster.entitytodto.dto.UserDto;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<UserDto> getAllUserDtoByCreateQuery() {
        String query = "SELECT new vn.techmaster.entitytodto.dto.UserDto(u.id, u.name, u.email) FROM User u";
        TypedQuery<UserDto> typedQuery = entityManager.createQuery(query, UserDto.class);
        return typedQuery.getResultList();
    }
    public List<UserDto> getAllUserDtoByCreateNamedQuery() {
        TypedQuery<UserDto> typedQuery = entityManager.createNamedQuery("getUserInfoOther", UserDto.class);
        return typedQuery.getResultList();
    }
}
