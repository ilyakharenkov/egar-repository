package com.example.finalproject.service.image;

import com.example.finalproject.domain.entity.image.Image;
import com.example.finalproject.domain.entity.instrument.Alignment;
import com.example.finalproject.domain.entity.instrument.Countersink;
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

    //���� � ������ �������� Alignment (���������).
    private static final String DIRECTORY_ALIGNMENT = System.getProperty("user.dir") + "/src/main/resources/storage/alignment";

    //���� � ������ �������� Countersink (��������).
    private static final String DIRECTORY_COUNTERSINK = System.getProperty("user.dir") + "/src/main/resources/storage/countersink";

    public List<Image> findAll() {
        return imageRepository.findAll();
    }

    public void save(Image image) {
        imageRepository.save(image);
    }

    //��������� ����� �� ��� ����.
    public File getImageDirectory(Long id) {
        for (Image image : findAll()) {
            if (image.getId().equals(id)) {
                var p = Paths.get(image.getDownloadLink(), image.getName());
                return p.toFile();
            }
        }
        return new File("");
    }

    //���������� �������� Image.
    public void saveImageAlignment(List<MultipartFile> multipartFileList, Alignment alignment) {
        multipartFileList.forEach(multipartFile -> {
            if (!multipartFile.isEmpty() && alignment != null) {
                writeFile(multipartFile, DIRECTORY_ALIGNMENT);
                var image = Image.builder()
                        .name(multipartFile.getOriginalFilename())
                        .downloadLink(DIRECTORY_ALIGNMENT)
                        .alignment(alignment)
                        .build();
                this.save(image);
            } else {
                System.out.println("File not found");
            }
        });
    }

    //���������� �������� Image.
    public void saveImageCountersink(List<MultipartFile> multipartFileList, Countersink countersink) {
        multipartFileList.forEach(multipartFile -> {
            if (!multipartFile.isEmpty()) {
                writeFile(multipartFile, DIRECTORY_COUNTERSINK);
                var image = Image.builder()
                        .name(multipartFile.getOriginalFilename())
                        .downloadLink(DIRECTORY_COUNTERSINK)
                        .countersink(countersink)
                        .build();
                this.save(image);
            } else {
                System.out.println("File not found");
            }
        });
    }


    //�������� � ������ �����.
    private void writeFile(MultipartFile multipartFile, String directory) {
        Path path = Paths.get(directory, multipartFile.getOriginalFilename());
        try {
            Files.write(path, multipartFile.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //�������� ����� �� ��������.
    public void deleteFile(List<Image> imageList) {
        imageList.forEach(image -> {
            var path = Paths.get(image.getDownloadLink(), image.getName());
            try {
                Files.delete(path);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
