package com.example.finalproject.service.archive;

import com.example.finalproject.domain.entity.archive.Archive;
import com.example.finalproject.domain.entity.rent.Rent;
import com.example.finalproject.domain.repository.archive.ArchiveRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@EnableScheduling
@AllArgsConstructor
public class ArchiveService {

    private final ArchiveRepository archiveRepository;

    public List<Archive> findAll(){
        return archiveRepository.findAll();
    }

    public void save(Archive archive){
        archiveRepository.save(archive);
    }

    public void deleteById(Long id){
        archiveRepository.deleteById(id);
    }

    @Scheduled(fixedRate = 60_000)
    @Transactional
    public void deleteArchiveTimeOut(){
        findAll().forEach(archive -> {
            if(archive.getDeleteArchive().equals(LocalDate.now())){
                deleteById(archive.getId());
            }
        });
    }
}
