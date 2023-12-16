package com.example.inventoryinstrument.service.renovation;

import com.example.inventoryinstrument.domain.entity.renovation.Renovation;
import com.example.inventoryinstrument.domain.repository.renovation.RenovationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class RenovationService {
    private final RenovationRepository renovationRepository;

    public List<Renovation> findAll() {
        return renovationRepository.findAll();
    }


    //Возвращает Renovation конкретного инструмента.
    public Renovation findRenovationByAlignmentId(Long id){
        return renovationRepository.findRenovationByAlignmentId(id).orElse(null);
    }

    public Renovation findRenovationByCountersinkId(Long id){
        return renovationRepository.findRenovationByCountersinkId(id).orElse(null);
    }

    //Создание экземпляра класса Renovation.
    public Renovation createObjectRenovation(Renovation renovation){
        return Renovation.builder()
                .countDay(renovation.getCountDay())
                .priceDiagnostics(renovation.getPriceDiagnostics())
                .descriptionResult(renovation.getDescriptionResult())
                .startRenovation(LocalDate.now())
                .endRenovation(LocalDate.now().plusDays(renovation.getCountDay()))
                .resultPrice(renovation.getResultPrice())
                .checkStatus(true)
                .build();
    }

    public void save(Renovation renovation) {
        renovationRepository.save(renovation);
    }

    public void update(Renovation renovation){
        renovationRepository.save(renovation);
    }
}
