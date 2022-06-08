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
import java.util.*;

@Service
public class FileService {
    // Path folder để upload file
    private final Path rootDir = Paths.get("uploads");

    public FileService() {
        createFolder(rootDir.toString());
    }

    // Tạo folder (trường hợp folder chưa tồn tại)
    public void createFolder(String path) {
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    // Xử lý phần upload file
    public String uploadFile(int id, MultipartFile file) {
        // Tạo folder tương ứng với user id
        Path userDir = Paths.get("uploads").resolve(String.valueOf(id));
        createFolder(userDir.toString());

        // Validate file
        validateFile(file);

        // Tạo path tương ứng với file Upload lên
        String genarateFileName = Instant.now().getEpochSecond() + "-" + UUID.randomUUID()
                + "." + Utils.getFileExtension(file.getOriginalFilename());
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

    // Xử lý phần read file
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

    // Lấy danh sách file upload của userI
    public List<String> getFiles(int id) {
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
                .map(File::getName)
                .sorted(Comparator.reverseOrder())
                .map(file -> "/api/v1/users/" + id + "/files/" + file)
                .toList();

        return filesPath;
    }

    // Xử lý phần xóa file
    public void deleteFile(int id, String fileName) {
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
    }
}
