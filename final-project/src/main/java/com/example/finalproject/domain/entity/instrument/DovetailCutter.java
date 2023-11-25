package com.example.finalproject.domain.entity.instrument;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DovetailCutter {

    private Long id;
    private String name;
    private Integer length;
    private Integer workLength;
    private Integer diameter;
    private Integer angle;
    private Integer angleRadius;
    private Integer lengthShapedPart;

//    private List<Image> images;
//    private Client client;


}
