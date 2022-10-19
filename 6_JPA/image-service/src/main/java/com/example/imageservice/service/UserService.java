package com.example.imageservice.service;

import com.example.imageservice.dto.UserDto;
import com.example.imageservice.entity.User;
import com.example.imageservice.exception.BadRequestException;
import com.example.imageservice.exception.NotFoundException;
import com.example.imageservice.repository.UserRepository;
import com.example.imageservice.request.CreateUserRequest;
import com.example.imageservice.request.UpdateUserRequest;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final FileService fileService;

    // Lấy danh sách user ở dạng DTO
    public List<UserDto> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    // Tìm kiếm user theo tên
    public List<UserDto> searchUser(String name) {
        return userRepository.findByNameContainsIgnoreCase(name)
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    // Lấy thông tin của user theo id
    public UserDto getUserById(int id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return modelMapper.map(user, UserDto.class);
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
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new BadRequestException("email = " + request.getEmail() + " is existed");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());
        user.setPassword(request.getPassword());

        userRepository.save(user);

        return modelMapper.map(user, UserDto.class);
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

        return modelMapper.map(user, UserDto.class);
    }


    public String uploadFile(int id, MultipartFile file) throws IOException {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) {
            throw new NotFoundException("user with id = " + id + " not found");
        }

        User user = userOptional.get();
        
        // Upload file
        String filePath = fileService.uploadFile(user, file);

        // Set lại thông tin cho user
        user.setAvatar(filePath);
        userRepository.save(user);

        return filePath;
    }

    // Xem file
    public byte[] readFile(int id, Integer fileId) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) {
            throw new NotFoundException("user with id = " + id + " not found");
        }
        return fileService.readFile(fileId);
    }

    // Lấy danh sách files upload của user
    public List<String> getFiles(int id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) {
            throw new NotFoundException("user with id = " + id + " not found");
        }

        return fileService.getFiles(id);
    }

    // Xóa file
    public void deleteFile(int id, Integer fileId) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) {
            throw new NotFoundException("user with id = " + id + " not found");
        }

        fileService.deleteFile(fileId);
    }
}
