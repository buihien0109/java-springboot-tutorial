package com.example.projection;

import com.example.projection.entity.User;
import com.example.projection.projection.UserInfo;
import com.example.projection.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProjectionApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    void insert_list_user_sample_test() {
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
    void get_all_user_info_test() {
        List<UserInfo> users = userRepository.getUsersBy(UserInfo.class);

        Assertions.assertThat(users.size()).isEqualTo(4);
        Assertions.assertThat(users).isNotNull();

        users.forEach(UserInfo::showInfo);
    }

    @Test
    void get_all_user_test() {
        List<User> users = userRepository.getUsersBy(User.class);

        users.forEach(System.out::println);

        Assertions.assertThat(users.size()).isEqualTo(4);
        Assertions.assertThat(users).isNotNull();
    }

    @Test
    void find_user_by_id_return_user_info_test() {
        UserInfo user = userRepository.getUserById(1, UserInfo.class);
        user.showInfo();

        Assertions.assertThat(user.getName()).isEqualTo("Bui Hien");
        Assertions.assertThat(user).isNotNull();
    }

    @Test
    void find_user_by_id_return_user_test() {
        User user = userRepository.getUserById(1, User.class);
        System.out.println(user);

        Assertions.assertThat(user.getName()).isEqualTo("Bui Hien");
        Assertions.assertThat(user).isNotNull();
    }
}
