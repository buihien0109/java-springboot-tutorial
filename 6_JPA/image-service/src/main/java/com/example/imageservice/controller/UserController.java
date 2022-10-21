package com.example.imageservice.controller;

import com.example.imageservice.dto.UserDto;
import com.example.imageservice.request.CreateUserRequest;
import com.example.imageservice.request.UpdateUserRequest;
import com.example.imageservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    // Upload file
    @PostMapping("/users/{id}/upload-file")
    public String uploadFile(@PathVariable int id,
                             @ModelAttribute("file") MultipartFile file) throws IOException {
        return userService.uploadFile(id, file);
    }

    // Xem thông tin file
    @GetMapping("/users/{id}/files/{fileId}")
    public ResponseEntity<?> readFile(@PathVariable int id, @PathVariable Integer fileId) {
        byte[] bytes = userService.readFile(id, fileId);
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);
    }

    // Lấy danh sách file của user
    @GetMapping("/users/{id}/files")
    public ResponseEntity<?> getFiles(@PathVariable int id) {
        List<String> files = userService.getFiles(id);
        return ResponseEntity.ok(files);
    }

    // Xóa file
    @DeleteMapping("/users/{id}/files/{fileId}")
    public ResponseEntity<?> deleteFile(@PathVariable int id, @PathVariable Integer fileId) {
        userService.deleteFile(id, fileId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
