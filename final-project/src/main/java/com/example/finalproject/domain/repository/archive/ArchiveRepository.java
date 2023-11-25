package com.example.finalproject.domain.repository.archive;

import com.example.finalproject.domain.entity.archive.Archive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchiveRepository extends JpaRepository<Archive, Long> {
}
