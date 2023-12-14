package com.example.inventoryinstrument.controller.image;

import com.example.inventoryinstrument.service.image.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.nio.file.Files;

@Controller
@AllArgsConstructor
public class ImageController {
    private final ImageService imageService;

    //ResponseEntity ������� ��� ������� Java, ������������ ��� ���������, ������, ���� ������.
    @GetMapping("/images/{id}")
    public ResponseEntity<?> getImageById(@PathVariable Long id) throws IOException {
        var file = imageService.getImageDirectory(id);
        return ResponseEntity.ok()
                .header("fileName", file.getName())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(file.length())
                .body(Files.readAllBytes(file.toPath()));
    }
}