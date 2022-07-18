package vn.techmaster.entitytodto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.techmaster.entitytodto.dto.UserDto;
import vn.techmaster.entitytodto.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, UserRepositoryCustom {
    @Query("select new vn.techmaster.entitytodto.dto.UserDto(u.id, u.name, u.email) from User u")
    List<UserDto> getAllUserDtoInfo();

    @Query(nativeQuery = true, name = "getUserInfo")
    List<UserDto> getAllUserDtoInfoOther();
}