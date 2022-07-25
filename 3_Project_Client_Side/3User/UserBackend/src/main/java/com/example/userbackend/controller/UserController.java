package com.example.userbackend.controller;

import com.example.userbackend.model.dto.UserDto;
import com.example.userbackend.model.request.CreateUserRequest;
import com.example.userbackend.model.request.UpdateAvatarRequest;
import com.example.userbackend.model.request.UpdatePasswordRequest;
import com.example.userbackend.model.request.UpdateUserRequest;
import com.example.userbackend.model.response.FileReturn;
import com.example.userbackend.model.response.UploadFileResponse;
import com.example.userbackend.serivice.FileService;
import com.example.userbackend.serivice.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    // Lấy danh sách user
    @GetMapping("/users")
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    // Tìm kiếm user theo tên
    @GetMapping("/users/search")
    public List<UserDto> searchUser(@RequestParam String name) {
        return userService.searchUser(name);
    }

    // Lấy chi tiết user theo id
    @GetMapping("/users/{id}")
    public UserDto getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    // Tạo user mới
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody CreateUserRequest request) {
        return userService.createUser(request);
    }

    // Xóa user
    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }

    // Cập nhật thông tin user
    @PutMapping("/users/{id}")
    public UserDto updateUser(@PathVariable int id,
                                        @RequestBody UpdateUserRequest request) {
        return userService.updateUser(id, request);
    }

    // Cập nhật mật khẩu mới
    @PutMapping("/users/{id}/update-password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePassword(@PathVariable int id,
                                            @RequestBody UpdatePasswordRequest request) {
        userService.updatePassword(id, request);
    }

    // Quên mật khẩu
    @PostMapping("/users/{id}/forgot-password")
    public String updatePassword(@PathVariable int id) {
        return userService.forgotPassword(id);
    }

    // Upload file
    @PostMapping("/users/{id}/upload-file")
    public String uploadFile(@PathVariable int id,
                                        @ModelAttribute("file") MultipartFile file) {
        return userService.uploadFile(id, file);
    }

    // Xem thông tin file
    @GetMapping(value = "users/{id}/files/{fileId}", produces = {MediaType.IMAGE_JPEG_VALUE})
    public byte[] readFile(@PathVariable int id, @PathVariable String fileId) {
        return userService.readFile(id, fileId);
    }
}

