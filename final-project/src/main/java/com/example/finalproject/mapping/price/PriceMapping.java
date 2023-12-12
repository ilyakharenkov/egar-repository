package com.example.finalproject.mapping.price;

import com.example.finalproject.domain.dto.price.PriceDto;
import com.example.finalproject.domain.entity.price.Price;
import org.springframework.stereotype.Service;

@Service
public class PriceMapping {

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
