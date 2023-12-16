package com.example.inventoryinstrument.mapping.instrument;

import com.example.inventoryinstrument.domain.dto.instrument.AlignmentDto;
import com.example.inventoryinstrument.domain.entity.instrument.Alignment;
import com.example.inventoryinstrument.domain.entity.price.Price;
import com.example.inventoryinstrument.mapping.price.PriceMapping;
import com.example.inventoryinstrument.service.price.PriceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AlignmentMapper {

    private final PriceService priceService;
    private final PriceMapping priceMapping;

    public AlignmentDto convertToDto(Alignment alignment, Price price) {
        return AlignmentDto.builder()
                .id(alignment.getId())
                .name(alignment.getName())
                .diameter(alignment.getDiameter())
                .length(alignment.getLength())
                .workLength(alignment.getWorkLength())
                .angle(alignment.getAngle())
                .checkStatus(alignment.getCheckStatus())
                .priceDto(priceService.convertPriceDto(price))
                .rent(alignment.getRent())
                .imageList(alignment.getImageList())
                .renovation(alignment.getRenovation())
                .build();
    }

    public Alignment convertToEntity(AlignmentDto alignmentDto) {
        return Alignment.builder()
                .id(alignmentDto.getId())
                .name(alignmentDto.getName())
                .diameter(alignmentDto.getDiameter())
                .length(alignmentDto.getLength())
                .workLength(alignmentDto.getWorkLength())
                .angle(alignmentDto.getAngle())
                .checkStatus(alignmentDto.getCheckStatus())
                .price(priceMapping.convertToEntity(alignmentDto.getPriceDto()))
                .rent(alignmentDto.getRent())
                .imageList(alignmentDto.getImageList())
                .renovation(alignmentDto.getRenovation())
                .build();
    }

}
