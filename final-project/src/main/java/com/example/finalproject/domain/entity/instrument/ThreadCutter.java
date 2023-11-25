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
public class ThreadCutter {
    private Long id;
    private String name;
    private Integer length;
    private Integer workLength;
    private Integer diameter;
    private Integer shankDiameter;
    private Integer pitch;
    private Integer numberOfTeeth;

//    private List<Image> images;
//    private Client client;


}
