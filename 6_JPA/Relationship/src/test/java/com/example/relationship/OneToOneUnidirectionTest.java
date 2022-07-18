package com.example.relationship;

import com.example.relationship.model.one_to_one.unidirection.Address;
import com.example.relationship.model.one_to_one.unidirection.User;
import com.example.relationship.repository.AddressRepository;
import com.example.relationship.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class OneToOneUnidirectionTest {
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Thêm nội dung vào database")
    void add_data_test() {
        userRepository.deleteAll();

        Address address = new Address();
        address.setStreet("Nguyễn Trãi");
        address.setCity("Hà Nội");

        Address address1 = new Address();
        address.setStreet("Tố Hữu");
        address.setCity("Hà Nội");

        User user = new User();
        user.setName("Bùi Hiên");
        user.setAddress(address);

        User user1 = new User();
        user1.setName("Phương Loan");
        user1.setAddress(address1);

        userRepository.save(user);
        userRepository.save(user1);
    }

    @Test
    @DisplayName("Kiểm tra số lượng user hiện đang có")
    void count_user_test() {
        long count = userRepository.count();
        assertThat(count).isPositive();
        assertThat(count).isEqualTo(2L);
    }

    @Test
    @DisplayName("Tìm kiếm user theo tên")
    void get_user_by_name() {
        User userData = userRepository.findByName("Bùi Hiên");

        assertThat(userData).isNotNull();
        assertThat(userData.getName()).isEqualTo("Bùi Hiên");
    }

    @Test
    @DisplayName("Tìm kiếm address theo street")
    void get_address_by_street() {
        Address address = addressRepository.findByStreet("Tố Hữu");

        assertThat(address).isNotNull();
        assertThat(address.getCity()).isEqualTo("Hà Nội");
    }

    @Test
    @DisplayName("Xóa user theo tên")
    void delete_user_by_name() {
        User user = userRepository.findByName("Bùi Hiên");
        assertThat(user).isNotNull();

        userRepository.delete(user);

        long count = userRepository.count();
        assertThat(count).isEqualTo(1L);
    }
}
