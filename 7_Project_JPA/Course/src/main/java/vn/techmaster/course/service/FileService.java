package vn.techmaster.course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;
import vn.techmaster.course.entity.Image;
import vn.techmaster.course.exception.BadRequestException;
import vn.techmaster.course.repository.ImageRepository;
import vn.techmaster.course.util.Utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileService {
    @Autowired
    private ImageRepository imageRepository;

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

    public String uploadFile(MultipartFile file) {
        // Validate file
        validateFile(file);

        // Tạo path tương ứng với file Upload lên
        String genarateFileId = UUID.randomUUID().toString();
        File serverFile = new File(rootDir + "/" + genarateFileId);

        try {
            // Sử dụng Buffer để lưu dữ liệu từ file
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));

            stream.write(file.getBytes());
            stream.close();

            String filePath = "/api/files/" + genarateFileId;

            // Tạo đối tượng image
            Image image = Image.builder()
                    .id(genarateFileId)
                    .link(filePath)
                    .build();

            // Lưu lại file vào trong database
            imageRepository.save(image);

            return "/api/files/" + genarateFileId;
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

    public byte[] readFile(String fileId) {
        try {
            // Lấy đường dẫn file tương ứng file_id
            Path file = rootDir.resolve(fileId);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                InputStream stream = resource.getInputStream();
                byte[] bytes = StreamUtils.copyToByteArray(stream);

                stream.close();

                return bytes;
            } else {
                throw new RuntimeException("Không thể đọc file: " + fileId);
            }
        } catch (Exception e) {
            throw new RuntimeException("Không thể đọc file : " + fileId);
        }
    }
}
