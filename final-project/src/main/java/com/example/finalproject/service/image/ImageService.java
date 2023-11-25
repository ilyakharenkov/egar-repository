package com.example.finalproject.service.image;

import com.example.finalproject.domain.entity.image.Image;
import com.example.finalproject.domain.repository.image.ImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@AllArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public List<Image> findAll() {
        return imageRepository.findAll();
    }

    //Добавить валидацию на проверку multipartFile.getOriginalFilename().
    public void save(List<MultipartFile> multipartFileList) {
        multipartFileList.forEach(multipartFile -> {
            if (!multipartFile.isEmpty()) {
                String directory = System.getProperty("user.dir") + "/src/main/resources/storage";
                Path path = Paths.get(directory, multipartFile.getOriginalFilename());
                try {
                    Files.write(path, multipartFile.getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                imageRepository.save(Image.builder()
                        .name(multipartFile.getOriginalFilename())
                        .downloadLink(directory)
                        .build());
            } else {
                System.out.println("File not found");
            }
        });
    }

    public File getImageDirectory(Long id) {
        for (Image image : findAll()) {
            if (image.getId().equals(id)) {
                var p = Paths.get(image.getDownloadLink(), image.getName());
                return p.toFile();
            }
        }
        return new File("");
    }


}
