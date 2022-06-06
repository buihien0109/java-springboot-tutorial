package com.example.blogbackend.service.admin;

import com.example.blogbackend.model.Role;
import com.example.blogbackend.model.User;
import com.example.blogbackend.model.dto.UserDto;
import com.example.blogbackend.model.mapper.UserMapper;
import com.example.blogbackend.model.request.CreateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class UserAdminService {
    private List<User> users = new ArrayList<>();

    @Autowired
    private Random rd;

    public UserAdminService() {
        init();
    }

    public void init() {
        // Khởi tạo role
        Role roleAdmin = new Role(1, "ADMIN");
        Role roleUser = new Role(2, "USER");

        // Khởi tạo user
        User user1 = new User(1, "Bùi Hiên", "hien@techmaster.vn", "111", null, true, new ArrayList<>());
        user1.getRoles().add(roleAdmin);
        user1.getRoles().add(roleUser);
        users.add(user1);

        User user2 = new User(2, "Thu Hằng", "thuhangvnua@gmail.com", "111", null, true, new ArrayList<>());
        user2.getRoles().add(roleUser);
        users.add(user2);
    }

    public List<User> getUsers() {
        return users;
    }

    // Trả về danh sách user ở dạng userDto
    public List<UserDto> getAllUsers() {
        return users.stream()
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }

    public UserDto getUserById(int id) {
        Optional<User> userOptional = findUser(id);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("Không tìm thấy user có id = " + id);
        }

        return UserMapper.toUserDto(userOptional.get());
    }

    // Admin tạo user mới thì không cần kích hoạt email
    // Password mặc định là : 111
    // Trạng thái mặc định là : true
    public UserDto createUser(CreateUserRequest request) {
        // Kiểm tra đã tồn tại user với email định tạo hay chưa
        if (findUser(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email = " + request.getEmail() + " is existed!");
        }

        // Tạo user mới và chèn thông tin cho user
        User user = new User();
        user.setId(rd.nextInt(100));
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setStatus(true);
        user.setPassword("111");
        user.setRoles(request.getRoles());

        // Thêm user vừa tạo vào trong List để quản lý
        users.add(user);

        // Trả về user vừa tạo
        return UserMapper.toUserDto(user);
    }

    // Cập nhật thông tin user
//    private UserDto updateUser(int id, UpdateUserRequest) {}

    // Xóa user
    private void deleteUser(int id) {
        if (findUser(id).isEmpty()) {
            throw new RuntimeException("Không tìm thấy user có id = " + id);
        }

        users.removeIf(u -> u.getId() == id);
    }

    public Optional<User> findUser(int id) {
        return users.stream().filter(u -> u.getId() == id).findFirst();
    }

    public Optional<User> findUser(String email) {
        return users.stream().filter(u -> u.getEmail().equals(email)).findFirst();
    }
}
