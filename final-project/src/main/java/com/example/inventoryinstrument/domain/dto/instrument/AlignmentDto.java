package com.example.inventoryinstrument.domain.dto.instrument;

import com.example.inventoryinstrument.domain.dto.price.PriceDto;
import com.example.inventoryinstrument.domain.entity.image.Image;
import com.example.inventoryinstrument.domain.entity.renovation.Renovation;
import com.example.inventoryinstrument.domain.entity.rent.Rent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlignmentDto {

    private Long id;

    private String name;

    private Integer diameter;

    private Integer length;

    private Integer workLength;

    private Integer angle;

    private Boolean checkStatus;

    private PriceDto priceDto;

    private Rent rent;

    private List<Image> imageList;

    private List<Renovation> renovation;

}
