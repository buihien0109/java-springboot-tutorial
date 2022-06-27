package vn.techmaster.entitytodto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.techmaster.entitytodto.dto.UserDto;
import vn.techmaster.entitytodto.entity.User;
import vn.techmaster.entitytodto.repository.UserRepository;
import vn.techmaster.entitytodto.service.UserService;

import java.util.List;

@SpringBootTest
class EntityToDtoApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
     void insertUserTest() {
        User user = User.builder().name("Bui Hien").email("hien@gmail.com").password("111").build();
        User user1 = User.builder().name("Thu Hang").email("hang@gmail.com").password("222").build();
        User user2 = User.builder().name("Minh Duy").email("duy@gmail.com").password("333").build();
        User user3 = User.builder().name("Phuong Loan").email("loan@gmail.com").password("444").build();

        userRepository.save(user);
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
    }

    @Test
    void get_list_user_dto_by_mapstruct_test() {
        List<UserDto> userDtos = userService.getUsers();
        Assertions.assertThat(userDtos.size()).isEqualTo(4L);
    }

    @Test
    void get_list_user_dto_by_jpql_query_test() {
        List<UserDto> userDtos = userRepository.getAllUserDtoInfo();
        userDtos.forEach(System.out::println);
        Assertions.assertThat(userDtos.size()).isEqualTo(4L);
    }

    @Test
    void get_list_user_dto_by_named_native_query() {
        List<UserDto> userDtos = userRepository.getAllUserDtoInfoOther();
        userDtos.forEach(System.out::println);
        Assertions.assertThat(userDtos.size()).isEqualTo(4L);
    }

    @Test
    void get_list_user_dto_by_custom_repo() {
        List<UserDto> userDtos = userRepository.getAllUserDtoByCreateQuery();
        userDtos.forEach(System.out::println);
        Assertions.assertThat(userDtos.size()).isEqualTo(4L);
    }

    @Test
    void get_list_user_dto_by_named_query() {
        List<UserDto> userDtos = userRepository.getAllUserDtoByCreateNamedQuery();
        userDtos.forEach(System.out::println);
        Assertions.assertThat(userDtos.size()).isEqualTo(4L);
    }
}
