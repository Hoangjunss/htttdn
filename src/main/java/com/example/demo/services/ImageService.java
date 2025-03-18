package com.example.demo.services;

import com.example.demo.entities.Image;
import com.example.demo.entities.SanPham;
import com.example.demo.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ImageService {
    private final CloudinaryService cloudinaryService;
    private final ImageRepository imageRepository;

    @Autowired
    public ImageService(CloudinaryService cloudinaryService, ImageRepository imageRepository) {
        this.cloudinaryService = cloudinaryService;
        this.imageRepository = imageRepository;
    }

    public List<Image> getAllImageByProduct(SanPham sanPham){
        return imageRepository.findImageBySanPham(sanPham);
    }

    public String saveImage(MultipartFile image) {
        Map<String, Object> resultMap = cloudinaryService.upload(image);
        String imageUrl = (String) resultMap.get("url");
        return imageUrl;
    }

    public void deleteImage(String url) throws IOException {
        String publicId = cloudinaryService.getPublicId(url); // Trả về "abc123"
        Map result = cloudinaryService.delete(publicId);
    }

    private Integer getGenerationId() {
        UUID uuid = UUID.randomUUID();
        return (int) (uuid.getMostSignificantBits() & 0xFFFFFFFFL);
    }

}
