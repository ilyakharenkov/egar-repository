package com.example.inventoryinstrument.mapping.instrument;

import com.example.inventoryinstrument.domain.dto.instrument.CountersinkDto;
import com.example.inventoryinstrument.domain.entity.instrument.Countersink;
import com.example.inventoryinstrument.domain.entity.price.Price;
import com.example.inventoryinstrument.mapping.price.PriceMapping;
import com.example.inventoryinstrument.service.price.PriceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CountersinkMapper {

    private final PriceService priceService;
    private final PriceMapping priceMapping;

    public CountersinkDto convertToDto(Countersink countersink, Price price) {
        return CountersinkDto.builder()
                .id(countersink.getId())
                .name(countersink.getName())
                .diameter(countersink.getDiameter())
                .length(countersink.getLength())
                .workLength(countersink.getWorkLength())
                .angle(countersink.getAngle())
                .checkStatus(countersink.getCheckStatus())
                .priceDto(priceService.convertPriceDto(price))
                .rent(countersink.getRent())
                .imageList(countersink.getImageList())
                .renovation(countersink.getRenovation())
                .build();
    }

    public Countersink convertToEntity(CountersinkDto countersinkDto) {
        return Countersink.builder()
                .id(countersinkDto.getId())
                .name(countersinkDto.getName())
                .diameter(countersinkDto.getDiameter())
                .length(countersinkDto.getLength())
                .workLength(countersinkDto.getWorkLength())
                .angle(countersinkDto.getAngle())
                .checkStatus(countersinkDto.getCheckStatus())
                .price(priceMapping.convertToEntity(countersinkDto.getPriceDto()))
                .rent(countersinkDto.getRent())
                .imageList(countersinkDto.getImageList())
                .renovation(countersinkDto.getRenovation())
                .build();
    }

}
