package com.example.inventoryinstrument.renovation.service;

import com.example.inventoryinstrument.renovation.model.Renovation;
import com.example.inventoryinstrument.renovation.repository.RenovationRepository;
import com.example.inventoryinstrument.renovation.mapper.RenovationMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class RenovationService {
    private final RenovationRepository renovationRepository;

    private final RenovationMapper renovationMapper;

    public List<Renovation> findAll() {
        return renovationRepository.findAll();
    }

    //Возвращает Renovation конкретного инструмента.
    public Renovation findRenovationByAlignmentId(Long id) {
        return renovationRepository.findRenovationByAlignmentId(id).orElse(null);
    }

    public Renovation findRenovationByCountersinkId(Long id) {
        return renovationRepository.findRenovationByCountersinkId(id).orElse(null);
    }

    //Создание экземпляра класса Renovation.
    public Renovation createObjectRenovation(Renovation renovation) {
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

    //Проверяем существовал ли такой объект или нет.
    //Если да то обновляем данные и возваращаем назад.
    //Если нет то создаем и возваращаем новый.
    public Renovation validationOfRenovationForSave(Renovation oldRenovation, Renovation renovation) {

        if (oldRenovation != null && !oldRenovation.getCheckStatus()) {
            //Перенес в mapper сеттеры.
            return renovationMapper.convertToUpdateRenovation(oldRenovation, renovation);
        } else {
            return this.createObjectRenovation(renovation);
        }
    }

    public void save(Renovation renovation) {
        renovationRepository.save(renovation);
    }

    public void update(Renovation renovation) {
        renovationRepository.save(renovation);
    }
}
