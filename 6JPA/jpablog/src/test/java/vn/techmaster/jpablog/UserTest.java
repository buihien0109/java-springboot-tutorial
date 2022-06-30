package vn.techmaster.jpablog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import vn.techmaster.jpablog.model.dto.UserDto;
import vn.techmaster.jpablog.model.dto.UserInfo;
import vn.techmaster.jpablog.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void get_user_dto_by_id_test() {
        UserDto userDto = userRepository.getUserById(1);
        System.out.println(userDto);
    }

    @Test
    void get_user_info_by_id_test() {
        UserInfo userInfo = userRepository.getUserInfoById(1);
        System.out.println(userInfo);
    }
}
