package com.example.inventoryinstrument.profit.model;

import com.example.inventoryinstrument.rent.model.RentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfitDto {

    private Long id;

    //Дней аренды.
    private Integer dayRent;

    //Сумма выручки с аренды.
    private Double income;

    //Сумма налога.
    private Double tax;

    private RentDto rent;


}
