package com.example.finalproject.domain.entity.instrument.cutter;

import com.example.finalproject.domain.entity.image.Image;
import com.example.finalproject.domain.entity.instrument.Instrument;
import com.example.finalproject.domain.entity.instrument.Status;
import com.example.finalproject.domain.entity.person.Person;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SlotCutter {

    private Long id;
    private String name;
    private Integer length;
    private Integer workLength;
    private Integer diameter;
    private Integer shankDiameter;
    private Integer lengthShapedPart;
    private Integer angle;
    private Integer angleRadius;
    private Instrument instrument;
    private List<Image> images;
    private Person person;
    private Status status;

}
