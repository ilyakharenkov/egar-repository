package com.example.finalproject.domain.dto.instrument;

import com.example.finalproject.domain.dto.price.PriceDto;
import com.example.finalproject.domain.entity.image.Image;
import com.example.finalproject.domain.entity.renovation.Renovation;
import com.example.finalproject.domain.entity.rent.Rent;
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
