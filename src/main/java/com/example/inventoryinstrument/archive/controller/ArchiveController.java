package com.example.inventoryinstrument.archive.controller;

import com.example.inventoryinstrument.archive.model.ArchiveDto;
import com.example.inventoryinstrument.archive.service.ArchiveService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/archive")
@AllArgsConstructor
public class ArchiveController {

    private final ArchiveService archiveService;

    @GetMapping
    public ResponseEntity<List<ArchiveDto>> findAll() {
        var archiveList = archiveService.findAll();
        return ResponseEntity.ok().body(archiveList);
    }

}
