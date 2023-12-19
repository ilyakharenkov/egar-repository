package com.example.inventoryinstrument.renovation.mapper;

import com.example.inventoryinstrument.alignment.mapper.AlignmentMapper;
import com.example.inventoryinstrument.countersink.mapper.CountersinkMapper;
import com.example.inventoryinstrument.renovation.model.Renovation;
import com.example.inventoryinstrument.renovation.model.RenovationDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class RenovationMapper {

    private final AlignmentMapper alignmentMapper;

    private final CountersinkMapper countersinkMapper;


    public RenovationDto convertToDto(Renovation renovation) {
        return validationNullableOfInstrument(renovation);
    }

    public Renovation convertToEntity(RenovationDto renovationDto) {
        return Renovation.builder()
                .id(renovationDto.getId())
                .startRenovation(renovationDto.getStartRenovation())
                .endRenovation(renovationDto.getEndRenovation())
                .countDay(renovationDto.getCountDay())
                .priceDiagnostics(renovationDto.getPriceDiagnostics())
                .descriptionResult(renovationDto.getDescriptionResult())
                .resultPrice(renovationDto.getResultPrice())
                .checkStatus(renovationDto.getCheckStatus())
                .alignment(alignmentMapper.convertToEntity(renovationDto.getAlignment()))
                .countersink(countersinkMapper.convertToEntity(renovationDto.getCountersink()))
                .build();
    }

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

    public RenovationDto validationNullableOfInstrument(Renovation renovation) {
        if (renovation.getAlignment() != null) {
            return RenovationDto.builder()
                    .id(renovation.getId())
                    .startRenovation(renovation.getStartRenovation())
                    .endRenovation(renovation.getEndRenovation())
                    .countDay(renovation.getCountDay())
                    .priceDiagnostics(renovation.getPriceDiagnostics())
                    .descriptionResult(renovation.getDescriptionResult())
                    .resultPrice(renovation.getResultPrice())
                    .checkStatus(renovation.getCheckStatus())
                    .alignment(alignmentMapper.convertToDto(renovation.getAlignment(), renovation.getAlignment().getPrice()))
                    .build();
        }
        if (renovation.getCountersink() != null) {
            return RenovationDto.builder()
                    .id(renovation.getId())
                    .startRenovation(renovation.getStartRenovation())
                    .endRenovation(renovation.getEndRenovation())
                    .countDay(renovation.getCountDay())
                    .priceDiagnostics(renovation.getPriceDiagnostics())
                    .descriptionResult(renovation.getDescriptionResult())
                    .resultPrice(renovation.getResultPrice())
                    .checkStatus(renovation.getCheckStatus())
                    .countersink(countersinkMapper.convertToDto(renovation.getCountersink(), renovation.getCountersink().getPrice()))
                    .build();
        }
        return RenovationDto.builder().build();
    }

}
