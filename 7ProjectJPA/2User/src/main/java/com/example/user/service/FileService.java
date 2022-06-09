package com.example.user.service;

import com.example.user.entity.Image;
import com.example.user.exception.BadRequestException;
import com.example.user.model.response.FileReturn;
import com.example.user.repository.ImageRepository;
import com.example.user.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final int IMAGE_OF_PAGE = 12;
    private final Path rootDir = Paths.get("uploads");

    @Autowired
    private ImageRepository imageRepository;

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

        // Lưu thông tin file vào database
        Image image = new Image();
        image.setUrl();
        image.setUserId(id);
        imageRepository.save(image);

        File serverFile = new File(userDir + "/" + genarateFileName);

        try {
            // Sử dụng Buffer để lưu dữ liệu từ file
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));

            stream.write(file.getBytes());
            stream.close();

            return "/api/v1/users/" + id + "/files/" + genarateFileName;
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

    public byte[] readFile(int id, String fileName) {
        // Lấy đường dẫn file tương ứng với user_id
        Path userPath = rootDir.resolve(String.valueOf(id));

        // Kiểm tra đường dẫn file có tồn tại hay không
        if (!Files.exists(userPath)) {
            throw new RuntimeException("Không thể đọc file : " + fileName);
        }

        try {
            // Lấy đường dẫn file tương ứng với user_id và file_name
            Path file = userPath.resolve(fileName);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return StreamUtils.copyToByteArray(resource.getInputStream());
            } else {
                throw new RuntimeException("Không thể đọc file: " + fileName);
            }
        } catch (Exception e) {
            throw new RuntimeException("Không thể đọc file : " + fileName);
        }
    }

    public FileReturn getFiles(int id, int page) {
        // Lấy đường dẫn file tương ứng với user_id
        Path userPath = rootDir.resolve(String.valueOf(id));

        // Kiểm tra đường dẫn file có tồn tại hay không
        if (!Files.exists(userPath)) {
            throw new RuntimeException("Lỗi khi lấy danh sách file");
        }

        // Lấy danh sách tất cả file theo userId
        Optional<File[]> optionalFiles = Optional.ofNullable(userPath.toFile().listFiles());
        List<File> files = new ArrayList<>();
        if (optionalFiles.isPresent()) {
            files = List.of(optionalFiles.get());
        }

        // Danh sách file path
        List<String> filesPath = files
                .stream()
                .skip((long) (page - 1) * IMAGE_OF_PAGE)
                .limit(IMAGE_OF_PAGE)
                .map(File::getName)
                .sorted((a, b) -> b.compareTo(a))
                .map(file -> "/api/v1/users/" + id + "/files/" + file)
                .toList();

        // Tính tổng số page
        int totalPage = (int) Math.ceil((double) files.size() / IMAGE_OF_PAGE);

        return new FileReturn(filesPath, totalPage);
    }

    public int deleteFile(int id, String fileName) {
        // Lấy đường dẫn file tương ứng với user_id
        Path userPath = rootDir.resolve(String.valueOf(id));

        // Kiểm tra đường dẫn file có tồn tại hay không
        if (!Files.exists(userPath)) {
            throw new RuntimeException("File " + fileName + " không tồn tại");
        }

        File serverFile = new File(userPath + "/" + fileName);

        // Kiểm tra xem file có tồn tại hay không
        if (!serverFile.exists()) {
            throw new RuntimeException("File " + fileName + " không tồn tại");
        }

        // Tiến hành xóa file
        serverFile.delete();

        return getTotalPage(userPath);
    }

    public int getTotalPage(Path path) {
        Optional<File[]> optionalFiles = Optional.ofNullable(path.toFile().listFiles());
        List<File> files = new ArrayList<>();
        if (optionalFiles.isPresent()) {
            files = List.of(optionalFiles.get());
        }

        int totalPage = (int) Math.ceil((double) files.size() / IMAGE_OF_PAGE);
        return totalPage;
    }
}
