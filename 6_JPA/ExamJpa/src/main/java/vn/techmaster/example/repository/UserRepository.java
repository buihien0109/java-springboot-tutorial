package vn.techmaster.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.techmaster.example.dto.UserDto;
import vn.techmaster.example.dto.UserInfo;
import vn.techmaster.example.entity.User;

import java.lang.annotation.Native;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Method query + Convert to Dto (trong service)
    User getUserById(Long id);

    // JPQL Query
    @Query("select new vn.techmaster.example.dto.UserDto(u.id, u.name, u.email) from User u where u.id = ?1")
    UserDto getUserDtoByIdUsingJPQL(Long id);

    // Native Query
    @Query(nativeQuery = true, name = "getUserDtoByIdUsingNativeQuery")
    UserDto getUserDtoByIdUsingNativeQuery(Long id);

    // Projection
    UserInfo getUserInfoById(Long id);
}