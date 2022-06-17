package com.example.jobhunt.service;

import com.example.jobhunt.entity.Image;
import com.example.jobhunt.exception.BadRequestException;
import com.example.jobhunt.repo.ImageRepo;
import com.example.jobhunt.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Sort;
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
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FileService {
    // Path folder để upload file
    private final Path rootPath = Paths.get("uploads");

    @Autowired
    private ImageRepo imageRepo;

    public FileService() {
        createFolder(rootPath.toString());
    }

    // Tạo folder
    public void createFolder(String path) {
        File folder = new File(path);
        if(!folder.exists()) {
            folder.mkdirs();
        }
    }

    // Upload file
    public String uploadFile(int id, MultipartFile file) {
        // B1 : Tạo folder tương ứng userId
        Path userDir = Paths.get("uploads").resolve(String.valueOf(id));
        createFolder(userDir.toString());

        // B2 : Validate file
        validate(file);

        // B3 : Tạo path tương ứng với fileUpload
        String generateFileId = UUID.randomUUID().toString();
        File fileServer = new File(userDir + "/" + generateFileId);

        try {
            // Sử dụng Buffer để lưu dữ liệu
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(fileServer));

            stream.write(file.getBytes());
            stream.close();

            return "/api/v1/users/" + id + "/files/" + generateFileId;
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi upload file");
        }
    }

    public void validate(MultipartFile file) {
        // Kiểm tra tên file
        String fileName = file.getOriginalFilename();
        if(fileName == null || fileName.equals("")) {
            throw new BadRequestException("Tên file không hợp lệ");
        }

        // Kiểm tra đuôi file
        String fileExtension = Utils.getFileExtension(fileName);
        if(!Utils.checkFileExtension(fileExtension)) {
            throw new BadRequestException("File không hợp lệ");
        }

        // Kiểm tra size (upload dưới 2MB)
        if((double) file.getSize() / 1_000_000L > 2) {
            throw new BadRequestException("File không được vượt quá 2MB");
        }
    }

    // Xem file
    public byte[] readFile(int id, String fileId) {
        // Lấy đường dẫn file tương ứng với user_id
        Path userPath = rootPath.resolve(String.valueOf(id));

        // Kiểm tra userPath có tồn tại hay không
        if(!Files.exists(userPath)) {
            throw new RuntimeException("Lỗi khi đọc file " + fileId);
        }

        try {
            Path file = userPath.resolve(fileId);
            Resource resource = new UrlResource(file.toUri());

            if(resource.exists() || resource.isReadable()) {
                return StreamUtils.copyToByteArray(resource.getInputStream());
            } else {
                throw new RuntimeException("Lỗi khi đọc file " + fileId);
            }

        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi đọc file " + fileId);
        }
    }

    // Xóa file
    public boolean deleteFile(int id, String fileId) {
        // Lấy đường dẫn file tương ứng với user_id
        Path userPath = rootPath.resolve(String.valueOf(id));

        // Kiểm tra userPath có tồn tại hay không
        if(!Files.exists(userPath)) {
            throw new RuntimeException("Lỗi khi đọc file " + fileId);
        }

        // Tạo đường dẫn đến file
        File file = userPath.resolve(fileId).toFile();
        if(!file.exists()) {
            throw new RuntimeException("file " + fileId + " không tồn tại");
        }

        return file.delete();
    }

    // Lấy danh sách file
    public List<String> getFiles(int id) {
        List<Image> images = imageRepo.getByUserIdOrderByUploadedAtDesc(id, Sort.by(Sort.Direction.DESC));

        return images.stream()
                .map(file -> "/api/v1/users" + id + "/files/" + file)
                .collect(Collectors.toList());
    }
}
