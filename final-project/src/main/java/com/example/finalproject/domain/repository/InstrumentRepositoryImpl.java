package com.example.finalproject.domain.repository;

import com.example.finalproject.domain.entity.instrument.Instrument;
import com.example.finalproject.domain.entity.instrument.Status;
import com.example.finalproject.domain.entity.instrument.cutter.CylinderCutter;
import com.example.finalproject.domain.entity.instrument.cutter.SphericalCutter;
import com.example.finalproject.domain.entity.instrument.cutter.ToroidalCutter;
import com.example.finalproject.domain.entity.person.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class InstrumentRepositoryImpl implements InstrumentRepository {

    private final Instrument instrument = new Instrument();

    {
        addInstrument();
    }

    //Заглушка для проверки добавления и отображения
    private void addInstrument() {
        instrument.addCylinderCutter(
                new CylinderCutter(1L, "Name Cylinder", 10, 40, 20, 20, 10, 0, new Instrument(), new ArrayList<>(), new Person(), new Status())
        );
        instrument.addSphericalCutter(
                new SphericalCutter(1L, "Name Sphere", 10, 40, 20, 20, 10, 0, new Instrument(), new ArrayList<>(), new Person(), new Status())
        );
    }


    @Override
    public Instrument getInstrument() {
        return this.instrument;
    }
}
