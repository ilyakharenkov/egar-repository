package com.example.inventoryinstrument.image.service;

import com.example.inventoryinstrument.alignment.model.Alignment;
import com.example.inventoryinstrument.countersink.model.Countersink;
import com.example.inventoryinstrument.image.model.Image;
import com.example.inventoryinstrument.image.repository.ImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@AllArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    //Путь к файлам сущности Alignment (Центровки).
//    private static final String DIRECTORY_ALIGNMENT = System.getProperty("user.dir") + "/src/main/resources/storage/alignment/";

    //Тестовый путь к директории.
    private static final String DIRECTORY_ALIGNMENT = ResourceUtils.CLASSPATH_URL_PREFIX + "storage/alignment/";

    //Путь к файлам сущности Countersink (Зенковки).
    private static final String DIRECTORY_COUNTERSINK = System.getProperty("user.dir") + "/src/main/resources/storage/countersink";

    public List<Image> findAll() {
        return imageRepository.findAll();
    }

    public void save(Image image) {
        imageRepository.save(image);
    }

    //Получение файла по его пути.
    public File getImageDirectory(Long id) throws FileNotFoundException {
        for (Image image : findAll()) {
            if (image.getId().equals(id)) {
                return ResourceUtils.getFile(image.getDownloadLink() + image.getName());
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
//        Path path = Paths.get(directory, multipartFile.getOriginalFilename());
//        try {
//            Files.write(path, multipartFile.getBytes());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        // Не может сохранить файл:
        // class path resource [storage/alignment/**.png] cannot be resolved to absolute file path because it does not exist
        try {
            var file = ResourceUtils.getFile(directory + multipartFile.getOriginalFilename());
            System.out.println(file);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(multipartFile.getBytes());
            fos.close();
//            Files.write(file.toPath(), multipartFile.getBytes());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //Удаление файла из ресурсов.
    public void deleteFile(List<Image> imageList) {
        imageList.forEach(image -> {
            try {
                var path = ResourceUtils.getFile(image.getDownloadLink() + image.getName()).toPath();
                Files.delete(path);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
