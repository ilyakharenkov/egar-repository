package com.example.finalproject.service.profit;

import com.example.finalproject.domain.entity.profit.Profit;
import com.example.finalproject.domain.entity.rent.Rent;
import com.example.finalproject.domain.repository.profit.ProfitRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProfitService {
    private final ProfitRepository profitRepository;

    public List<Profit> findAll(){
        return profitRepository.findAll();
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
