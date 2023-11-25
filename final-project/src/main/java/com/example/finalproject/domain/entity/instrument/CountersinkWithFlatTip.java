package com.example.finalproject.domain.entity.instrument;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CountersinkWithFlatTip {

    private Long id;
    private String name;
    private Integer length;
    private Integer workLength;
    private Integer diameter;
    private Integer shankDiameter;
    private Integer tipDiameter;
    private Integer shankLength;
    private Integer tipAngle;

//    private List<Image> images;
//    private Client client;

}
