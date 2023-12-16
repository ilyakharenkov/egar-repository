package com.example.inventoryinstrument.countersink.model;

import com.example.inventoryinstrument.image.model.ImageDto;
import com.example.inventoryinstrument.price.model.PriceDto;
import com.example.inventoryinstrument.rent.model.Rent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountersinkDto {

    private Long id;

    private String name;

    private Integer diameter;

    private Integer length;

    private Integer workLength;

    private Integer angle;

    private Boolean checkStatus;

    private PriceDto priceDto;

    private Rent rent;

    private List<ImageDto> imageDtoList;

}
