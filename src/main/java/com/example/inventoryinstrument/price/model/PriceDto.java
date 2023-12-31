package com.example.inventoryinstrument.price.model;

import com.example.inventoryinstrument.alignment.model.AlignmentDto;
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

    private Integer markup;

    private Double priceRentOfDay;

    private AlignmentDto alignmentDto;

    //Себестоимость.
    private Double costPrice;

    //Дополнительная цена.
    private Double extraPrice;

    //Итоговая цена продажи.
    private Double sell;

}
