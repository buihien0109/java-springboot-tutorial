package com.example.user.service;

import com.example.user.entity.User;
import com.example.user.exception.BadRequestException;
import com.example.user.exception.NotFoundException;
import com.example.user.model.dto.UserDto;
import com.example.user.model.mapper.UserMapper;
import com.example.user.model.request.CreateUserRequest;
import com.example.user.model.request.UpdatePasswordRequest;
import com.example.user.model.request.UpdateUserRequest;
import com.example.user.repository.ImageRepository;
import com.example.user.repository.UserRepository;
import com.example.user.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private final EmailService emailService;
    private final FileService fileService;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;

    // Lấy danh sách user ở dạng DTO
    public List<UserDto> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }

    // Tìm kiếm user theo tên
    public List<UserDto> searchUser(String name) {
        return userRepository.findByNameContainsIgnoreCase(name)
                .stream()
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }

    // Lấy thông tin của user theo id
    public UserDto getUserById(int id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return UserMapper.toUserDto(user);
        }

        throw new NotFoundException("user with id = " + id + " not found");
    }

    // Xóa user
    public void deleteUser(int id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) {
            throw new NotFoundException("user with id = " + id + " not found");
        }

        userRepository.delete(userOptional.get());
    }

    // Tạo user mới
    public UserDto createUser(CreateUserRequest request) {
        if (userRepository.findUserByEmail(request.getEmail()).isPresent()) {
            throw new BadRequestException("email = " + request.getEmail() + " is existed");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());
        user.setPassword(request.getPassword());

        userRepository.save(user);

        return UserMapper.toUserDto(user);
    }

    // Cập nhật thông tin của user
    public UserDto updateUser(int id, UpdateUserRequest request) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) {
            throw new NotFoundException("user with id = " + id + " not found");
        }

        User user = userOptional.get();
        user.setName(request.getName());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());

        userRepository.save(user);

        return UserMapper.toUserDto(user);
    }

    // Cập nhật password mới
    public void updatePassword(int id, UpdatePasswordRequest request) {
        // Kiểm tra có tồn tại hay không
        Optional<User> userOptional = userRepository.findById(id);
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

        userRepository.save(user);
    }

    // Quên mật khẩu
    // TODO : Sử dụng Transaction + RollBack nếu có lỗi
    public String forgotPassword(int id) {
        // Kiểm tra user có tồn tại hay không
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new NotFoundException("Id = " + id + " is not exist!");
        }
        // Random chuỗi password mới cho user
        String newPassword = Utils.generatePassword(3);

        // Lấy thông tin của user và đặt lại password mới cho user
        User user = userOptional.get();
        user.setPassword(newPassword);

        userRepository.save(user);

        // Send Mail
        // emailService.sendMail(user.getEmail(), "Đổi mật khẩu", "Mật khẩu mới của bạn là : " + newPassword);

        // Trả về thông tin password mới
        return newPassword;
    }

    // Upload file
    public String uploadFile(int id, MultipartFile file) {
        // Kiểm tra có tồn tại hay không
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new NotFoundException("user with id = " + id + " not found");
        }

        // Upload file
        String filePath = fileService.uploadFile(id, file);

        // Set lại thông tin cho user
        User user = userOptional.get();
        user.setAvatar(filePath);
        userRepository.save(user);

        return filePath;
    }

    // Xem file
    public byte[] readFile(int id, String fileId) {
        return fileService.readFile(id, fileId);
    }
}
