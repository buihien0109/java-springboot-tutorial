package com.example.resizeimage.service;

import com.example.resizeimage.exception.BadRequestException;
import com.example.resizeimage.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
@RequiredArgsConstructor
public class FileService {
    // Check validate file
    public void validateFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();

        // Kiểm tra tên file
        if (fileName == null || fileName.equals("")) {
            throw new BadRequestException("Tên file không hợp lệ");
        }

        // Kiểm tra đuôi file
        String fileExtension = FileUtils.getFileExtension(fileName);
        if (!FileUtils.checkFileExtension(fileExtension)) {
            throw new BadRequestException("Định dạng file không hợp lệ");
        }

        // Kiểm tra dung lượng file (<= 2MB)
        if ((double) file.getSize() / 1_048_576L > 10) {
            throw new BadRequestException("File không được vượt quá 10MB");
        }
    }
}
