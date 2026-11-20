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

    public Map<?,?> uploadImageToCloudinary(MultipartFile file, String folderName) throws IOException {
        if(allowedContentTypes.contains(file.getContentType())){
            Map<String, Object> option = ObjectUtils.asMap(
                    "folder", folderName,
                    "resource_type", "auto"
            );

            return cloudinary.uploader().upload(file.getBytes(),option);
        }
        else{
            return null;
        }
    }

    public void deleteImageFromCloudinary(String publicId) throws IOException {
         cloudinary.uploader().destroy(publicId,ObjectUtils.emptyMap());
    }
}
