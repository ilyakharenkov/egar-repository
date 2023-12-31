package com.example.inventoryinstrument.alignment.service;

import com.example.inventoryinstrument.alignment.mapper.AlignmentMapper;
import com.example.inventoryinstrument.alignment.model.Alignment;
import com.example.inventoryinstrument.alignment.model.AlignmentDto;
import com.example.inventoryinstrument.alignment.repository.AlignmentRepository;
import com.example.inventoryinstrument.renovation.service.RenovationService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class AlignmentService {
    private final AlignmentRepository alignmentRepository;
    private final RenovationService renovationService;
    private final AlignmentMapper alignmentMapper;

    public List<AlignmentDto> findAll() {
        return alignmentRepository.findAll()
                .stream()
                .map(alignment -> alignmentMapper.convertToDto(alignment, alignment.getPrice()))
                .collect(Collectors.toList());
    }

    //Свободный инструмент.
    public List<AlignmentDto> findFreeAlignment() {
        return alignmentRepository.findFreeAlignment()
                .stream()
                .map(alignment -> alignmentMapper.convertToDto(alignment, alignment.getPrice()))
                .collect(Collectors.toList());
    }

    public Alignment findById(Long id) {
        return alignmentRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Not found Alignment"));
    }

    public void save(Alignment alignment) {
        alignmentRepository.save(alignment);
    }

    public void deleteById(Long id) {
        alignmentRepository.deleteById(id);
    }

    //Изменение статуса инструмента при его аренде.
    public void checkStatusFalse(Alignment alignment) {
        alignment.setCheckStatus(false);
        this.update(alignment);
    }

    public void update(Alignment alignment) {
        alignmentRepository.save(alignment);
    }

    //Првоерка обслуживания инструмента на 5:30 утра.
    @Scheduled(cron = "0 30 5 * * *", zone = "Europe/Moscow")
    @Transactional
    public void endRenovationTime() {
        renovationService.findAllForScheduled().forEach(renovation -> {
            if (renovation.getAlignment() != null
                    && renovation.getCheckStatus()
                    && renovation.getEndRenovation().compareTo(LocalDate.now()) <= 0) {

                renovation.setCheckStatus(false);
                renovation.getAlignment().setCheckStatus(true);
                this.update(renovation.getAlignment());
                renovationService.update(renovation);
                log.info(String.format("Обслуживание %s закончилось", renovation.getAlignment().getName()));
            }
        });
    }

}
