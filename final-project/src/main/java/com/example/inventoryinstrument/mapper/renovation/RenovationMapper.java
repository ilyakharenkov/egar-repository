package com.example.inventoryinstrument.mapper.renovation;

import com.example.inventoryinstrument.domain.entity.renovation.Renovation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class RenovationMapper {

    public Renovation convertToUpdateRenovation(Renovation oldRenovation, Renovation newRenovation) {
        oldRenovation.setCountDay(newRenovation.getCountDay());
        oldRenovation.setPriceDiagnostics(newRenovation.getPriceDiagnostics());
        oldRenovation.setDescriptionResult(newRenovation.getDescriptionResult());
        oldRenovation.setStartRenovation(LocalDate.now());
        oldRenovation.setEndRenovation(LocalDate.now().plusDays(newRenovation.getCountDay()));
        oldRenovation.setResultPrice(newRenovation.getResultPrice());
        oldRenovation.setCheckStatus(true);
        return oldRenovation;
    }

}
