package com.example.finalproject.domain.entity.instrument;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ToroidalCutter {

    private Long id;
    private String name;
    private Integer diameter;
    private Integer length;
    private Integer workLength;
    private Integer shankLength;
    private Integer shankDiameter;
    private Integer shankTaperAngle;
    private Integer radius;

//    private List<Image> images;
//    private Client client;


}
