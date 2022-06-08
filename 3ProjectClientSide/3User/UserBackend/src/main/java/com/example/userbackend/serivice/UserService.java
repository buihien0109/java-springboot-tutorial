package com.example.userbackend.serivice;

import com.example.userbackend.exception.BadRequestException;
import com.example.userbackend.exception.NotFoundException;
import com.example.userbackend.model.User;
import com.example.userbackend.model.dto.UserDto;
import com.example.userbackend.model.mapper.UserMapper;
import com.example.userbackend.model.request.CreateUserRequest;
import com.example.userbackend.model.request.UpdateAvatarRequest;
import com.example.userbackend.model.request.UpdatePasswordRequest;
import com.example.userbackend.model.request.UpdateUserRequest;
import com.example.userbackend.utils.Utils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class UserService {
    // Tạo danh sách user để quản lý
    private List<User> users;

    // Inject bean để gửi email
    private final EmailService emailService;

    // Inject bean để gửi upload file
    private final FileService fileService;

    public UserService(EmailService emailService, FileService fileService) {
        this.emailService = emailService;
        this.fileService = fileService;
        init();
    }

    // Tạo 1 số user
    public void init() {
        users = new ArrayList<>();
        users.add(new User(1, "Bùi Hiên", "buihien01091997@gmail.com", "0344005816", "Tỉnh Thái Bình", null, "111"));
        users.add(new User(2, "Nguyễn Thu Hằng", "thuhangvnua@gmail.com", "0123456789", "Tỉnh Nam Định", null, "222"));
        users.add(new User(3, "Bùi Phương Loan", "hien@techmaster.vn", "0123456789", "Tỉnh Hưng Yên", null, "333"));
    }

    // Lấy danh sách user ở dạng DTO
    public List<UserDto> getUsers() {
        return users
                .stream()
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }

    // Tìm kiếm user theo tên
    public List<UserDto> searchUser(String name) {
        return users
                .stream()
                .filter(user -> user.getName().toLowerCase().contains(name.toLowerCase()))
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }

    // Lấy thông tin của user theo id
    public UserDto getUserById(int id) {
        Optional<User> userOptional = findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return UserMapper.toUserDto(user);
        }

        throw new NotFoundException("user with id = " + id + " not found");
    }

    // Xóa user
    public void deleteUser(int id) {
        Optional<User> userOptional = findById(id);

        if (userOptional.isEmpty()) {
            throw new NotFoundException("user with id = " + id + " not found");
        }

        users.removeIf(user -> user.getId() == id);
    }

    // Tạo user mới
    public UserDto createUser(CreateUserRequest request) {
        if (findByEmail(request.getEmail()).isPresent()) {
            throw new BadRequestException("email = " + request.getEmail() + " is existed");
        }

        Random rd = new Random();
        User user = new User();
        user.setId(rd.nextInt(100));
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());
        user.setPassword(request.getPassword());

        users.add(user);

        return UserMapper.toUserDto(user);
    }

    // Cập nhật thông tin của user
    public UserDto updateUser(int id, UpdateUserRequest request) {
        Optional<User> userOptional = findById(id);

        if (userOptional.isEmpty()) {
            throw new NotFoundException("user with id = " + id + " not found");
        }

        User user = userOptional.get();
        user.setName(request.getName());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());

        return UserMapper.toUserDto(user);
    }

    // Cập nhật password mới
    public void updatePassword(int id, UpdatePasswordRequest request) {
        // Kiểm tra có tồn tại hay không
        Optional<User> userOptional = findById(id);
        if (userOptional.isEmpty()) {
            throw new NotFoundException("user with id = " + id + " not found");
        }

        User user = userOptional.get();
        // Kiểm tra oldPassword có đúng không
        if (!user.getPassword().equals(request.getOldPassword())) {
            throw new BadRequestException("old password is incorrect!");
        }

        // Kiểm tra oldPassword có = newPassword không
        if (request.getNewPassword().equals(request.getOldPassword())) {
            throw new BadRequestException("old password and new password cannot be the same!");
        }

        // Cập nhật newPassword cho user tương ứng
        user.setPassword(request.getNewPassword());
    }

    // Quên mật khẩu
    public String forgotPassword(int id) {
        // Kiểm tra user có tồn tại hay không
        if (findById(id).isEmpty()) {
            throw new NotFoundException("Id = " + id + " is not exist!");
        }
        // Random chuỗi password mới cho user
        String newPassword = Utils.generatePassword(3);

        // Lấy thông tin của user và đặt lại password mới cho user
        User user = findById(id).get();
        user.setPassword(newPassword);

        // Send Mail
        emailService.sendMail(user.getEmail(), "Đổi mật khẩu", "Mật khẩu mới của bạn là : " + newPassword);

        // Trả về thông tin password mới
        return newPassword;
    }

    // HELPER METHOD : Tìm kiếm user theo id --> return Optional
    public Optional<User> findById(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst();
    }

    // HELPER METHOD : Tìm kiếm user theo email --> return Optional
    public Optional<User> findByEmail(String email) {
        return users.stream().filter(user -> user.getEmail().equals(email)).findFirst();
    }

    // Cập nhật avatar cho user
    public void updateAvatar(int id, UpdateAvatarRequest request) {
        // Kiểm tra có tồn tại hay không
        Optional<User> userOptional = findById(id);
        if (userOptional.isEmpty()) {
            throw new NotFoundException("user with id = " + id + " not found");
        }

        User user = userOptional.get();

        // Update lại avatar
        user.setAvatar(request.getAvatar());
    }

    // Upload file
    public String uploadFile(int id, MultipartFile file) {
        // Kiểm tra có tồn tại hay không
        Optional<User> userOptional = findById(id);
        if (userOptional.isEmpty()) {
            throw new NotFoundException("user with id = " + id + " not found");
        }

        User user = userOptional.get();

        String filePath = fileService.uploadFile(id, file);
        user.setAvatar(filePath);

        return filePath;
    }

    // Xem file
    public byte[] readFile(int id, String fileName) {
        return fileService.readFile(id, fileName);
    }
}
