package com.example.userbackend.serivice;

import com.example.userbackend.exception.BadRequestException;
import com.example.userbackend.exception.NotFoundException;
import com.example.userbackend.model.User;
import com.example.userbackend.model.response.FileReturn;
import com.example.userbackend.model.response.UploadFileResponse;
import com.example.userbackend.utils.Utils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FileService {
    private final Path rootDir = Paths.get("uploads");

    public FileService() {
        createFolder(rootDir.toString());
    }

    public void createFolder(String path) {
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    public String uploadFile(int id, MultipartFile file) {
        // Tạo folder tương ứng với user id
        Path userDir = Paths.get("uploads").resolve(String.valueOf(id));
        createFolder(userDir.toString());

        // Validate file
        validateFile(file);

        // Tạo path tương ứng với file Upload lên
        String genarateFileId = UUID.randomUUID().toString();
        File serverFile = new File(userDir + "/" + genarateFileId);

        try {
            // Sử dụng Buffer để lưu dữ liệu từ file
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));

            stream.write(file.getBytes());
            stream.close();

            return "/api/v1/users/" + id + "/files/" + genarateFileId;
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi upload file");
        }
    }

    // Một số validate với file
    public void validateFile(MultipartFile file) {
        // Kiểm tra file
        String fileName = file.getOriginalFilename();
        if (fileName == null || fileName.equals("")) {
            throw new BadRequestException("Tên file không hợp lệ");
        }

        // Lấy extension file
        String fileExtension = Utils.getFileExtension(fileName);

        // Kiểm tra extension file có hợp lệ hay không
        if (!Utils.checkFileExtension(fileExtension)) {
            throw new BadRequestException("File không hợp lệ");
        }

        // Check size file
        if ((double) file.getSize() / 1_000_000L > 2) {
            throw new BadRequestException("File không được vượt quá 2MB");
        }
    }

    public byte[] readFile(int id, String fileId) {
        // Lấy đường dẫn file tương ứng với user_id
        Path userPath = rootDir.resolve(String.valueOf(id));

        // Kiểm tra đường dẫn file có tồn tại hay không
        if (!Files.exists(userPath)) {
            throw new RuntimeException("Không thể đọc file : " + fileId);
        }

        try {
            // Lấy đường dẫn file tương ứng với user_id và file_name
            Path file = userPath.resolve(fileId);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return StreamUtils.copyToByteArray(resource.getInputStream());
            } else {
                throw new RuntimeException("Không thể đọc file: " + fileId);
            }
        } catch (Exception e) {
            throw new RuntimeException("Không thể đọc file : " + fileId);
        }
    }
}
