package com.example.finalproject.service.price;

import com.example.finalproject.domain.dto.instrument.AlignmentDto;
import com.example.finalproject.domain.dto.price.PriceDto;
import com.example.finalproject.domain.entity.price.Price;
import com.example.finalproject.domain.repository.price.PriceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PriceService {

    public void executeResult(List<AlignmentDto> alignmentDtoList) {
        alignmentDtoList.forEach(alignmentDto -> {

            //Наценка.
            Integer markup = 40;

            //Аренда.
            Integer rent = 5;

            //Расходы на реализацию.
            var expenses = alignmentDto.getPriceDto().getExpenses();

            //Закупочная цена у производителя.
            var purchase = alignmentDto.getPriceDto().getPurchase();

            //Себестоимость.
            var costPrice = executeCostPrice(expenses, purchase);

            //Дополнительная цена.
            var extraPrice = executeExtraPrice(costPrice, markup);

            //Цена продажи.
            var sell = executeSell(costPrice, extraPrice);

            var rental = executeRental(sell, rent);

            var priceDto = PriceDto.builder()
                    .id(alignmentDto.getPriceDto().getId())
                    .expenses(expenses)
                    .purchase(purchase)
                    .costPrice(costPrice)
                    .markup(40)
                    .extraPrice(extraPrice)
                    .sell(sell)
                    .rentOfDay(rental)
                    .build();

            alignmentDto.setPriceDto(priceDto);
        });
    }

    //Расчет полной себестоимсоти.
    private Double executeCostPrice(Double expenses, Double purchase) {
        return expenses + purchase;
    }

    //Расчет дополнительной цены.
    private Double executeExtraPrice(Double costPrice, Integer markup){
        return costPrice * markup / 100;
    }

    //Расчет цены для продаджи.
    private Double executeSell(Double costPrice, Double extraPrice){
        return costPrice + extraPrice;
    }

    //Расчет цены за один день аренды.
    private Double executeRental(Double sell, Integer rental){
        return  sell * rental / 100;
    }



}
