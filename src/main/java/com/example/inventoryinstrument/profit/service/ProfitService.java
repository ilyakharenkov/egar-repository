package com.example.inventoryinstrument.profit.service;

import com.example.inventoryinstrument.profit.mapper.ProfitMapper;
import com.example.inventoryinstrument.profit.model.Profit;
import com.example.inventoryinstrument.profit.model.ProfitDto;
import com.example.inventoryinstrument.profit.repository.ProfitRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProfitService {
    private final ProfitRepository profitRepository;

    private final ProfitMapper profitMapper;

    public List<ProfitDto> findAll(){
        return profitRepository.findAll()
                .stream()
                .map(profitMapper::convertToDto)
                .collect(Collectors.toList());
    }

    //Расчет прибыли за 7 дней.
    public Integer findSumProfit(){
        return profitRepository.findSumProfit().orElse(0);
    }

    public void save(Profit profit){
        profitRepository.save(profit);
    }

    //Создание экземпляра класса Profit.
    public Profit createObjectProfit(Integer dayRent, Double priceDay){
        return Profit.builder()
                .dayRent(dayRent)
                .income(this.executeIncome(dayRent, priceDay))
                .tax(this.executeTax(this.executeIncome(dayRent, priceDay)))
                .build();
    }

    //Расчет дохода.
    private Double executeIncome(Integer dayRent, Double priceDay){
        return dayRent * priceDay;
    }

    //Расчет налога.
    private Double executeTax(Double income){
        return income * 13 / 100;
    }
}
