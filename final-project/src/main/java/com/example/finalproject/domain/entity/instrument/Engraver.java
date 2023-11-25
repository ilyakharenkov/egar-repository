package com.example.finalproject.domain.entity.instrument;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Engraver {
    private Long id;
    private String name;
    private Integer diameter;
    private Integer length;
    private Integer workLength;
    private Integer shankLength;
    private Integer shankDiameter;
    private Integer shankTaperAngle;
    private Integer angle;
    private Integer finalDiameter;

//    private List<Image> images;
//    private Client client;

}
