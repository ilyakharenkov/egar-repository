package com.example.inventoryinstrument.image.service;

import com.example.inventoryinstrument.alignment.model.Alignment;
import com.example.inventoryinstrument.countersink.model.Countersink;
import com.example.inventoryinstrument.image.model.Image;
import com.example.inventoryinstrument.image.repository.ImageRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    public List<Image> findAll() {
        return imageRepository.findAll();
    }

    public void deleteById(Long id) {
        imageRepository.deleteById(id);
    }

    public void save(Image image) {
        imageRepository.save(image);
    }

    //Получение файла по его пути.
    public Image getImageDirectory(Long id) {
        for (Image image : findAll()) {
            if (image.getId().equals(id)) {
                var i = imageRepository.findById(image.getId());
                return i.orElse(new Image());
            }
        }
        return new Image();
    }

    //Сохранение сущности Image для Alignment.
    @Transactional
    public void saveImageAlignment(List<MultipartFile> multipartFileList, Alignment alignment) {
        multipartFileList.forEach(multipartFile -> {
            try {
                Blob blob = new SerialBlob(multipartFile.getBytes());
                if (!multipartFile.isEmpty() && alignment != null) {
                    var image = Image.builder()
                            .name(multipartFile.getOriginalFilename())
                            .blob(blob)
                            .alignment(alignment)
                            .build();
                    this.save(image);
                }
            } catch (SQLException | IOException e) {
                log.info(e.getMessage());
            }
        });
    }

    //Сохранение сущности Image для Countersink.
    public void saveImageCountersink(List<MultipartFile> multipartFileList, Countersink countersink) {
        multipartFileList.forEach(multipartFile -> {
            try {
                Blob blob = new SerialBlob(multipartFile.getBytes());
                if (!multipartFile.isEmpty()) {
                    var image = Image.builder()
                            .name(multipartFile.getOriginalFilename())
                            .blob(blob)
                            .countersink(countersink)
                            .build();
                    this.save(image);
                }
            } catch (SQLException | IOException e) {
                log.info(e.getMessage());
            }
        });
    }

    //Удаление файла из ресурсов.
    public void deleteFile(List<Image> imageList) {
        imageList.forEach(image ->
                deleteById(image.getId())
        );
    }

    //Получение изображения alignment.jpg которое находится в ресурсах.
    public File getImageStaticAlignment() {
        try {
            return ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX
                    + "storage/alignment/alignment.jpg");
        } catch (IOException e) {
            log.info(e.getMessage());
        }
        return new File("");
    }

    //Получение изображения countersink.jpg которое находится в ресурсах.
    public File getImageStaticCountersink() {
        try {
            return ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX
                    + "storage/countersink/countersink.jpg");
        } catch (IOException e) {
            log.info(e.getMessage());
        }
        return new File("");
    }

}
