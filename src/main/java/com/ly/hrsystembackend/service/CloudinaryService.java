package com.ly.hrsystembackend.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryService {
    private final Cloudinary cloudinary;
    private final List<String> allowedContentTypes = Arrays.asList("image/png", "image/jpeg", "image/jpg");

    public Map<?, ?> uploadImageToCloudinary(MultipartFile file, String folderName) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File cannot be null or empty");
        }

        String contentType = file.getContentType();
        if (contentType == null || (!allowedContentTypes.contains(contentType) && !"multipart/form-data".equals(contentType))) {
            throw new IllegalArgumentException("Unsupported file type: " + contentType);
        }

        byte[] bytes = file.getBytes();
        if (!isPng(bytes) && !isJpeg(bytes)) {
            throw new IllegalArgumentException("File content is not a PNG or JPEG image");
        }

        Map<String, Object> options = ObjectUtils.asMap(
                "folder", folderName,
                "resource_type", "image"
        );

        return cloudinary.uploader().upload(bytes, options);
    }

    public void deleteImageFromCloudinary(String publicId) throws IOException {
         cloudinary.uploader().destroy(publicId,ObjectUtils.emptyMap());
    }

    private boolean isPng(byte[] bytes) {
        return bytes.length >= 8
                && (bytes[0] & 0xff) == 0x89
                && bytes[1] == 0x50
                && bytes[2] == 0x4e
                && bytes[3] == 0x47
                && bytes[4] == 0x0d
                && bytes[5] == 0x0a
                && bytes[6] == 0x1a
                && bytes[7] == 0x0a;
    }

    private boolean isJpeg(byte[] bytes) {
        return bytes.length >= 3
                && (bytes[0] & 0xff) == 0xff
                && (bytes[1] & 0xff) == 0xd8
                && (bytes[2] & 0xff) == 0xff;
    }
}
