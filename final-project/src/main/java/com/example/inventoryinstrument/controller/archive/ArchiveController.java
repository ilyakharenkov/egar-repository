package com.example.inventoryinstrument.controller.archive;

import com.example.inventoryinstrument.domain.entity.archive.Archive;
import com.example.inventoryinstrument.service.archive.ArchiveService;
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

        var archiveList = archiveService.findAll();

        if (archiveList.isEmpty()) {
            return ResponseEntity.ok().body(new ArrayList<>());
        } else {
            return ResponseEntity.ok()
                    .body(archiveList);
        }
    }

}
