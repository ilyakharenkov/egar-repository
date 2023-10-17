package com.example.finalproject.domain.entity.instrument;

import com.example.finalproject.domain.entity.instrument.cutter.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

//  Не уверен что так корректно делать, сделал для объединения всех инструментов и
//  для удобства что бы на Controller можно было
//  отправить все инструменты сразу.
//  Как вариант можно сделать через маркерный интерфейс или абстрактный класс.

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Instrument {

    private Long id;
    private List<CylinderCutter> cylinderCutters = new ArrayList<>();
    private List<SphericalCutter> sphericalCutters = new ArrayList<>();
    private List<ToroidalCutter> toroidalCutters = new ArrayList<>();
    private List<Engraver> engravers = new ArrayList<>();
    private List<Drill> drills = new ArrayList<>();
    private List<Alignment> alignments = new ArrayList<>();
    private List<Countersink> countersinks = new ArrayList<>();
    private List<CountersinkWithFlatTip> countersinkWithFlatTips = new ArrayList<>();
    private List<Tap> taps = new ArrayList<>();
    private List<ThreadCutter> threadCutters = new ArrayList<>();
    private List<ReverseRadiusCutter> reverseRadiusCutters = new ArrayList<>();
    private List<DovetailCutter> dovetailCutters = new ArrayList<>();
    private List<SlotCutter> slotCutters = new ArrayList<>();

    public Instrument addCylinderCutter(CylinderCutter cylinderCutter){
        this.cylinderCutters.add(cylinderCutter);
        cylinderCutter.setInstrument(this);
        return this;
    }
    public Instrument addSphericalCutter(SphericalCutter sphericalCutter){
        this.sphericalCutters.add(sphericalCutter);
        sphericalCutter.setInstrument(this);
        return this;
    }
    public Instrument addToroidalCutter(ToroidalCutter toroidalCutter){
        this.toroidalCutters.add(toroidalCutter);
        toroidalCutter.setInstrument(this);
        return this;
    }


}
