package com.example.finalproject.service.profit;

import com.example.finalproject.domain.dto.instrument.AlignmentDto;
import com.example.finalproject.domain.entity.profit.Profit;
import com.example.finalproject.domain.entity.rent.Rent;
import com.example.finalproject.domain.repository.profit.ProfitRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProfitService {
    private final ProfitRepository profitRepository;
    public void save(Rent rent, AlignmentDto alignmentDto) {
        var d = rent.getDayRent();
        var s = alignmentDto.getPriceDto().getRentOfDay();
        var r = d * s;
        rent.getProfit().setIncome(r);
        profitRepository.save(rent.getProfit());
    }
}
