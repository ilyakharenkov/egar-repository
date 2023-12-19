package com.example.inventoryinstrument.profit.mapper;


import com.example.inventoryinstrument.profit.model.Profit;
import com.example.inventoryinstrument.profit.model.ProfitDto;
import com.example.inventoryinstrument.rent.mapper.RentMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProfitMapper {
    private final RentMapper rentMapper;

    public ProfitDto convertToDto(Profit profit) {

        var rent = rentMapper.convertToDto(profit.getRent());

        return ProfitDto.builder()
                .id(profit.getId())
                .dayRent(profit.getDayRent())
                .income(profit.getIncome())
                .tax(profit.getTax())
                .rent(rent)
                .build();
    }


}
