package com.example.inventoryinstrument.service.instrument;

import com.example.inventoryinstrument.domain.dto.instrument.AlignmentDto;
import com.example.inventoryinstrument.domain.entity.instrument.Alignment;
import com.example.inventoryinstrument.domain.repository.instrument.AlignmentRepository;
import com.example.inventoryinstrument.mapper.instrument.AlignmentMapper;
import com.example.inventoryinstrument.service.renovation.RenovationService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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
        renovationService.findAll().forEach(renovation -> {
            if (renovation.getAlignment() != null) {
                if (renovation.getCheckStatus()) {
                    if (renovation.getEndRenovation().compareTo(LocalDate.now()) <= 0) {
                        renovation.setCheckStatus(false);
                        renovation.getAlignment().setCheckStatus(true);
                        this.update(renovation.getAlignment());
                        renovationService.update(renovation);
                    }
                }
            }
        });
    }

}
