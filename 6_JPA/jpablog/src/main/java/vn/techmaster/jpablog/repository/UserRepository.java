package vn.techmaster.jpablog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.techmaster.jpablog.entity.User;
import vn.techmaster.jpablog.model.dto.UserDto;
import vn.techmaster.jpablog.model.dto.UserInfo;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select new vn.techmaster.jpablog.model.dto.UserDto(u.id, u.name, u.email, u.avatar) from User u where u.id = :id")
    UserDto getUserById(@Param("id") Integer id);

    @Query("select new vn.techmaster.jpablog.model.dto.UserInfo(u.id, u.name, u.email, u.avatar, u.identityCard.expried, u.identityCard.issued) from User u where u.id = :id")
    UserInfo getUserInfoById(@Param("id") Integer id);
}