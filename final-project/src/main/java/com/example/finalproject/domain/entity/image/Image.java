package com.example.finalproject.domain.entity.image;

import com.example.finalproject.domain.entity.instrument.cutter.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Фотографии инструмента
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Image {

    private Long id;
    private String name;
    private String fileName;
    private Long size;
    private String contentType;
    private Byte[] bytes;

    //Ассоциации
    private CylinderCutter cylinderCutters;
    private SphericalCutter sphericalCutters;
    private ToroidalCutter toroidalCutters;
    private Engraver engravers;
    private Drill drills;
    private Alignment alignments;
    private Countersink countersinks;
    private CountersinkWithFlatTip countersinkWithFlatTips;
    private Tap taps;
    private ThreadCutter threadCutters;
    private ReverseRadiusCutter reverseRadiusCutters;
    private DovetailCutter dovetailCutters;
    private SlotCutter slotCutters;

}
