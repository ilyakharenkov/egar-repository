package com.example.finalproject.controller.image;

import com.example.finalproject.service.image.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Controller
@AllArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @GetMapping("/image")
    public String viewImage(Model model){
        model.addAttribute("listImage", imageService.findAll());
        return "image";
    }

    @PostMapping("/image/add")
    public String addImage(@RequestParam("listFile") List<MultipartFile> multipartFileList){
        imageService.save(multipartFileList);
        return "redirect:/image";
    }

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
