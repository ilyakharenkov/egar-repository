package com.example.inventoryinstrument.domain.repository.archive;

import com.example.inventoryinstrument.domain.entity.archive.Archive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchiveRepository extends JpaRepository<Archive, Long> {

}
