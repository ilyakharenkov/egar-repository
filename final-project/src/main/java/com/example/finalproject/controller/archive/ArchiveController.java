package com.example.finalproject.controller.archive;

import com.example.finalproject.domain.entity.archive.Archive;
import com.example.finalproject.service.archive.ArchiveService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class ArchiveController {

    private final ArchiveService archiveService;

    @GetMapping("/archive")
    public ResponseEntity<List<Archive>> findAll() {
        if (archiveService.findAll().isEmpty()) {
            return ResponseEntity.badRequest().body(new ArrayList<>());
        } else {
            return ResponseEntity.ok()
                    .body(archiveService.findAll());
        }
    }

}
