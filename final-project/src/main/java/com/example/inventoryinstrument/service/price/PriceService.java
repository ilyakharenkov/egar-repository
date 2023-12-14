package com.example.inventoryinstrument.service.price;

import com.example.inventoryinstrument.domain.dto.price.PriceDto;
import com.example.inventoryinstrument.domain.entity.price.Price;
import com.example.inventoryinstrument.domain.repository.price.PriceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PriceService {

    private final PriceRepository priceRepository;

    public PriceDto convertPriceDto(Price price){

        //Расходы на реализацию.
        var expenses = price.getExpenses();

        //Закупочная цена у производителя.
        var purchase = price.getPurchase();

        //Себестоимость.
        var costPrice = executeCostPrice(expenses, purchase);

        //Дополнительная цена.
        var extraPrice = executeExtraPrice(costPrice, price.getMarkup());

        //Цена продажи.
        var sell = executeSell(costPrice, extraPrice);


        return PriceDto.builder()
                .id(price.getId())
                .expenses(expenses)
                .purchase(purchase)
                .markup(price.getMarkup())
                .priceRentOfDay(price.getPriceRentOfDay())
                .costPrice(costPrice)
                .extraPrice(extraPrice)
                .sell(sell)
                .build();
    }

    //Расчет полной себестоимсоти.
    public Double executeCostPrice(Double expenses, Double purchase) {
        return expenses + purchase;
    }

    //Расчет дополнительной цены.
    public Double executeExtraPrice(Double costPrice, Integer markup){
        return costPrice * markup / 100;
    }

    //Расчет цены для продаджи.
    public Double executeSell(Double costPrice, Double extraPrice){
        return costPrice + extraPrice;
    }


}
