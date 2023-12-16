package com.example.inventoryinstrument.service.instrument;

import com.example.inventoryinstrument.domain.dto.instrument.CountersinkDto;
import com.example.inventoryinstrument.domain.entity.instrument.Countersink;
import com.example.inventoryinstrument.domain.repository.instrument.CountersinkRepository;
import com.example.inventoryinstrument.mapper.instrument.CountersinkMapper;
import com.example.inventoryinstrument.service.renovation.RenovationService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CountersinkService {
    private final CountersinkRepository countersinkRepository;
    private final RenovationService renovationService;
    private final CountersinkMapper countersinkMapper;

    public List<CountersinkDto> findAll() {
        return countersinkRepository.findAll()
                .stream()
                .map(countersink -> countersinkMapper.convertToDto(countersink, countersink.getPrice()))
                .collect(Collectors.toList());
    }

    //Свободный инструмент.
    public List<CountersinkDto> findFreeCountersink() {
        return countersinkRepository.findFreeCountersink()
                .stream()
                .map(countersink -> countersinkMapper.convertToDto(countersink, countersink.getPrice()))
                .collect(Collectors.toList());
    }

    public Countersink findById(Long id) {
        return countersinkRepository.findById(id).orElse(null);
    }

    public void save(Countersink countersink) {
        countersinkRepository.save(countersink);
    }

    public void deleteById(Long id) {
        countersinkRepository.deleteById(id);
    }

    //Изменение статуса инструмента при его аренде.
    public void checkStatusFalse(Countersink countersink) {
        countersink.setCheckStatus(false);
        this.update(countersink);
    }

    //Поправить редактирование, данный способ работает если совпадет id сущности.
    public void update(Countersink countersink) {
        countersinkRepository.save(countersink);
    }

    //В 6:30 утра.
    @Scheduled(cron = "0 30 6 * * *", zone = "Europe/Moscow")
    @Transactional
    public void endRenovationTime() {
        renovationService.findAll().forEach(renovation -> {
            if (renovation.getCountersink() != null) {
                if (renovation.getCheckStatus()) {
                    if (renovation.getEndRenovation().compareTo(LocalDate.now()) <= 0) {
                        System.out.println("Status countersink renovation.ftlh false");
                        renovation.setCheckStatus(false);
                        renovation.getCountersink().setCheckStatus(true);
                        this.update(renovation.getCountersink());
                        renovationService.update(renovation);
                    }
                }
            }
        });
    }


}
