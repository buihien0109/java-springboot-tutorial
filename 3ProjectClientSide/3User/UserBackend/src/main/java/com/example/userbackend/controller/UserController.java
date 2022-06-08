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
    private final FileService fileService;

    // Lấy danh sách user
    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        List<UserDto> userDtos = userService.getUsers();
        return ResponseEntity.ok(userDtos);
    }

    // Tìm kiếm user theo tên
    @GetMapping("/users/search")
    public ResponseEntity<?> searchUser(@RequestParam String name) {
        List<UserDto> userDtos = userService.searchUser(name);
        return ResponseEntity.ok(userDtos);
    }

    // Lấy chi tiết user theo id
    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id) {
        UserDto userDto = userService.getUserById(id);
        return ResponseEntity.ok(userDto);
    }

    // Tạo user mới
    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest request) {
        UserDto userDto = userService.createUser(request);
        return ResponseEntity.ok(userDto);
    }

    // Xóa user
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Cập nhật thông tin user
    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id,
                                        @RequestBody UpdateUserRequest request) {
        UserDto userDto = userService.updateUser(id, request);
        return ResponseEntity.ok(userDto);
    }

    // Cập nhật mật khẩu mới
    @PutMapping("/users/{id}/update-password")
    public ResponseEntity<?> updatePassword(@PathVariable int id,
                                            @RequestBody UpdatePasswordRequest request) {
        userService.updatePassword(id, request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Quên mật khẩu
    @PostMapping("/users/{id}/forgot-password")
    public ResponseEntity<?> updatePassword(@PathVariable int id) {
        String password = userService.forgotPassword(id);
        return ResponseEntity.ok(password);
    }

    // Upload file
    @PostMapping("/users/{id}/upload-file")
    public ResponseEntity<?> uploadFile(@PathVariable int id,
                                        @ModelAttribute("file") MultipartFile file) {
        String filePath = userService.uploadFile(id, file);
        return ResponseEntity.ok(filePath);
    }

    // Xem thông tin file
    @GetMapping("users/{id}/files/{fileName}")
    public ResponseEntity<?> readFile(@PathVariable int id, @PathVariable String fileName) {
        byte[] bytes = userService.readFile(id, fileName);
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);
    }

    // Lấy danh sách file của user
    @GetMapping("users/{id}/files")
    public ResponseEntity<?> getFiles(@PathVariable int id, @RequestParam int page) {
        FileReturn files = fileService.getFiles(id, page);
        return ResponseEntity.ok(files);
    }

    // Cập nhật avatar
    @PutMapping("users/{id}/update-avatar")
    public ResponseEntity<?> updateAvatar(@PathVariable int id, @RequestBody UpdateAvatarRequest request) {
        userService.updateAvatar(id, request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Xóa file
    @DeleteMapping("users/{id}/files/{fileName}")
    public ResponseEntity<?> deleteFile(@PathVariable int id, @PathVariable String fileName) {
        int totalPage = fileService.deleteFile(id, fileName);
        return ResponseEntity.ok(totalPage);
    }
}

