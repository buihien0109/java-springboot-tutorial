package com.example.userbackend.controller;

import com.example.userbackend.model.dto.UserDto;
import com.example.userbackend.model.request.CreateUserRequest;
import com.example.userbackend.model.request.UpdateAvatarRequest;
import com.example.userbackend.model.request.UpdatePasswordRequest;
import com.example.userbackend.model.request.UpdateUserRequest;
import com.example.userbackend.serivice.FileService;
import com.example.userbackend.serivice.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.print.Book;
import java.util.List;

@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final FileService fileService;

    @Operation(summary = "Lấy danh sách user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Tìm thấy danh sách user",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = UserDto.class))
                            )
                    }
            )
    })
    // Lấy danh sách user
    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        List<UserDto> userDtos = userService.getUsers();
        return ResponseEntity.ok(userDtos);
    }

    @Operation(summary = "Tìm kiếm thông tin user theo tên")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Tìm thấy thông tin user (có thể không hoặc nhiều user được tìm thấy)",
                    content = {
                            @Content(
                                mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = UserDto.class))
                            )
                    }
            )
    })
    // Tìm kiếm user theo tên
    @GetMapping("/users/search")
    public ResponseEntity<?> searchUser(@RequestParam String name) {
        List<UserDto> userDtos = userService.searchUser(name);
        return ResponseEntity.ok(userDtos);
    }

    @Operation(summary = "Tìm kiếm thông tin user theo id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Tìm thấy thông tin user theo id",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = UserDto.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Không tìm thấy user",
                    content = @Content
            )
    })
    // Lấy chi tiết user theo id
    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@Parameter(description = "id của user được tìm kiếm") @PathVariable int id) {
        UserDto userDto = userService.getUserById(id);
        return ResponseEntity.ok(userDto);
    }

    @Operation(summary = "Tạo user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Tạo user thành công",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = UserDto.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Tạo user không thành công (email đã tồn tại)",
                    content = @Content
            )
    })
    // Tạo user mới
    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest request) {
        UserDto userDto = userService.createUser(request);
        return ResponseEntity.ok(userDto);
    }

    @Operation(summary = "Xóa user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Xóa user thành công",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Xóa user không thành công (không tìm thấy user)",
                    content = @Content
            )
    })
    // Xóa user
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@Parameter(description = "id của user cần xóa") @PathVariable int id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Cập nhật thông tin user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Cập nhật thông tin user thành công",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Cập nhật thông tin user thành công (không tìm thấy user)",
                    content = @Content
            )
    })
    // Cập nhật thông tin user
    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@Parameter(description = "id của user cần cập nhật thông tin") @PathVariable int id,
                                        @RequestBody UpdateUserRequest request) {
        UserDto userDto = userService.updateUser(id, request);
        return ResponseEntity.ok(userDto);
    }

    @Operation(summary = "Cập nhật mật khẩu")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Cập nhật mật khẩu thành công",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Cập nhật mật khẩu thành công (sai mật khẩu cũ, hoặc mật khẩu cũ và mật khẩu mới trùng nhau)",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Cập nhật mật khẩu thành công (không tìm thấy user)",
                    content = @Content
            )
    })
    // Cập nhật mật khẩu mới
    @PutMapping("/users/{id}/update-password")
    public ResponseEntity<?> updatePassword(@Parameter(description = "id của user cần cập nhật mật khẩu") @PathVariable int id,
                                            @RequestBody UpdatePasswordRequest request) {
        userService.updatePassword(id, request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Quên mật khẩu")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Generate mật khẩu thành công",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = String.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Generate mật khẩu không thành công (không tìm thấy user)",
                    content = @Content
            )
    })
    // Quên mật khẩu
    @PostMapping("/users/{id}/forgot-password")
    public ResponseEntity<?> updatePassword(@Parameter(description = "id của user cần lấy lại mật khẩu") @PathVariable int id) {
        String password = userService.forgotPassword(id);
        return ResponseEntity.ok(password);
    }

    // Cập nhật avatar
    @PutMapping("/users/{id}/update-avatar")
    public ResponseEntity<?> updateAvatar(@PathVariable int id, @RequestBody UpdateAvatarRequest request) {
        userService.updateAvatar(id, request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Upload file
    @PostMapping("/users/{id}/upload-file")
    public ResponseEntity<?> uploadFile(@PathVariable int id,
                                        @ModelAttribute("file") MultipartFile file) {
        String filePath = userService.uploadFile(id, file);
        return ResponseEntity.ok(filePath);
    }

    // Xem thông tin file
    @GetMapping("/users/{id}/files/{fileName}")
    public ResponseEntity<?> readFile(@PathVariable int id, @PathVariable String fileName) {
        byte[] bytes = userService.readFile(id, fileName);
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
    public ResponseEntity<?> deleteFile(@PathVariable int id, @PathVariable String fileId) {
        userService.deleteFile(id, fileId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

