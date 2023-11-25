package com.example.finalproject.domain.dto.price;

import com.example.finalproject.domain.dto.instrument.AlignmentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PriceDto {

    private Long id;

    private Double purchase;

    private Double expenses;

    private AlignmentDto alignmentDto;

    //Себестоимость.
    private Double costPrice;

    //Наценка.
    private Integer markup;

    //Дополнительная цена.
    private Double extraPrice;

    //Итоговая цена продажи.
    private Double sell;

    //Цена за день аренды.
    private Double rentOfDay;

}
