package com.example.inventoryinstrument.archive.service;

import com.example.inventoryinstrument.archive.mapper.ArchiveMapper;
import com.example.inventoryinstrument.archive.model.Archive;
import com.example.inventoryinstrument.archive.model.ArchiveDto;
import com.example.inventoryinstrument.archive.repository.ArchiveRepository;
import com.example.inventoryinstrument.client.model.Client;
import com.example.inventoryinstrument.rent.model.Rent;
import com.example.inventoryinstrument.rent.service.RentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@EnableScheduling
@AllArgsConstructor
public class ArchiveService {
    private final ArchiveRepository archiveRepository;
    private final RentService rentService;
    private final ArchiveMapper archiveMapper;

    public List<ArchiveDto> findAll() {
        return archiveRepository.findAll()
                .stream()
                .map(archiveMapper::convertToDto)
                .collect(Collectors.toList());
    }

    public void save(Archive archive) {
        archiveRepository.save(archive);
    }

    public void deleteById(Long id) {
        archiveRepository.deleteById(id);
    }

    public void update(Archive archive) {
        archiveRepository.save(archive);
    }

    //Создание экземпляра класса Архив.
    public Archive createObjectArchive(Client client, Rent rent, Long idInstrument, String nameInstrument) {
        return Archive.builder()
                .createArchive(LocalDate.now())
                .deleteArchive(LocalDate.now().plusYears(1))
                .rent(rent)
                .idClient(client.getId())
                .nameClient(client.getFirstName())
                .surnameClient(client.getSurname())
                .idInstrument(idInstrument)
                .nameInstrument(nameInstrument)
                .startRental(LocalDate.now())
                .endRental(rentService.timeOutRent(LocalDate.now(), rent.getDayRent()))
                .dayRent(rent.getDayRent())
                .build();
    }

    //В 23:00 раз в месяц 1 числа.
    @Scheduled(cron = "0 0 23 1 * *", zone = "Europe/Moscow")
    @Transactional
    public void deleteArchiveTimeOut() {
        archiveRepository.findAll().forEach(archive -> {
            if (archive != null) {
                if (archive.getDeleteArchive().compareTo(LocalDate.now()) <= 0) {
                    rentService.deleteById(archive.getRent().getId());
                    deleteById(archive.getId());
                    log.info("Архив удален");
                }
            }
        });
    }
}
