package com.example.inventoryinstrument.service.image;

import com.example.inventoryinstrument.domain.entity.image.Image;
import com.example.inventoryinstrument.domain.entity.instrument.Alignment;
import com.example.inventoryinstrument.domain.entity.instrument.Countersink;
import com.example.inventoryinstrument.domain.repository.image.ImageRepository;
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

    //Путь к файлам сущности Alignment (Центровки).
    private static final String DIRECTORY_ALIGNMENT = System.getProperty("user.dir") + "/src/main/resources/storage/alignment";

    //Путь к файлам сущности Countersink (Зенковки).
    private static final String DIRECTORY_COUNTERSINK = System.getProperty("user.dir") + "/src/main/resources/storage/countersink";

    public List<Image> findAll() {
        return imageRepository.findAll();
    }

    public void save(Image image) {
        imageRepository.save(image);
    }

    //Получение файла по его пути.
    public File getImageDirectory(Long id) {
        for (Image image : findAll()) {
            if (image.getId().equals(id)) {
                var p = Paths.get(image.getDownloadLink(), image.getName());
                return p.toFile();
            }
        }
        return new File("");
    }

    //Сохранение сущности Image для Alignment.
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
            }
        });
    }

    //Сохранение сущности Image для Countersink.
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
            }
        });
    }


    //Создание и запись файла.
    private void writeFile(MultipartFile multipartFile, String directory) {
        Path path = Paths.get(directory, multipartFile.getOriginalFilename());
        try {
            Files.write(path, multipartFile.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Удаление файла из ресурсов.
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
