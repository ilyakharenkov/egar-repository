package com.example.inventoryinstrument.image.controller;

import com.example.inventoryinstrument.image.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;

@Controller
@AllArgsConstructor
public class ImageController {
    private final ImageService imageService;

    //ResponseEntity обертка для классов Java, определяющий код возварата, хедеры, тело ответа.
    @Transactional
    @GetMapping("/images/{id}")
    public ResponseEntity<?> getImageById(@PathVariable Long id) throws IOException, SQLException {
        var image = imageService.getImageDirectory(id);
        return ResponseEntity.ok()
                .header("fileName", image.getName())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(image.getBlob().getBinaryStream().readAllBytes());
    }

    //Изображение на стартовой странице инструмента Центровка.
    @GetMapping("/image-alignment")
    public ResponseEntity<?> getImageAlignment() throws IOException {
        var file = imageService.getImageStaticAlignment();
        return ResponseEntity.ok()
                .header("alignment-image", file.getName())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(file.length())
                .body(Files.readAllBytes(file.toPath()));
    }

    //Изображение на стартовой странице инструмента Зенковка.
    @GetMapping("/image-countersink")
    public ResponseEntity<?> getImageCountersink() throws IOException {
        var file = imageService.getImageStaticCountersink();
        return ResponseEntity.ok()
                .header("countersink-image", file.getName())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(file.length())
                .body(Files.readAllBytes(file.toPath()));
    }

}
