package com.example.inventoryinstrument.price.mapper;

import com.example.inventoryinstrument.price.model.PriceDto;
import com.example.inventoryinstrument.price.model.Price;
import org.springframework.stereotype.Service;

@Service
public class PriceMapper {

    public PriceDto convertToDto(Price price){
        return PriceDto.builder()
                .id(price.getId())
                .expenses(price.getExpenses())
                .purchase(price.getPurchase())
                .markup(price.getMarkup())
                .priceRentOfDay(price.getPriceRentOfDay())
                .build();
    }

    public Price convertToEntity(PriceDto priceDto){
        return Price.builder()
                .id(priceDto.getId())
                .expenses(priceDto.getExpenses())
                .purchase(priceDto.getPurchase())
                .markup(priceDto.getMarkup())
                .priceRentOfDay(priceDto.getPriceRentOfDay())
                .build();
    }


}
