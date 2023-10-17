package com.example.finalproject.domain.entity.person;

import com.example.finalproject.domain.entity.instrument.cutter.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

//  Таблица с Person, кто и когда взял взял инструмент, и когда вернул.

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private Long id;
    private String surname;
    private Integer tabNumber;
    private LocalDateTime took;
    private LocalDateTime gave;

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
