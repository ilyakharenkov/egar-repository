package com.example.inventoryinstrument.countersink.mapper;

import com.example.inventoryinstrument.countersink.model.CountersinkDto;
import com.example.inventoryinstrument.countersink.model.Countersink;
import com.example.inventoryinstrument.price.model.Price;
import com.example.inventoryinstrument.image.mapper.ImageMapper;
import com.example.inventoryinstrument.price.mapper.PriceMapper;
import com.example.inventoryinstrument.price.service.PriceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CountersinkMapper {

    private final PriceService priceService;
    private final PriceMapper priceMapper;

    private final ImageMapper imageMapper;

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
                .imageDtoList(countersink.getImageList().stream().map(imageMapper::convertToDto).toList())
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
                .price(priceMapper.convertToEntity(countersinkDto.getPriceDto()))
                .rent(countersinkDto.getRent())
                .imageList(countersinkDto.getImageDtoList().stream().map(imageMapper::convertToEntity).toList())
                .build();
    }

}
