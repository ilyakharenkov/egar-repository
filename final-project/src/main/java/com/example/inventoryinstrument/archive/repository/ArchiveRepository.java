package com.example.inventoryinstrument.archive.repository;

import com.example.inventoryinstrument.archive.model.Archive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchiveRepository extends JpaRepository<Archive, Long> {

}
