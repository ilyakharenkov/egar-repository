package com.example.finalproject.domain.entity.instrument;

import com.example.finalproject.domain.entity.instrument.cutter.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Статус и состояние инструмента

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Status {

    private Long id;
    private String description;
    private Boolean isState;

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
