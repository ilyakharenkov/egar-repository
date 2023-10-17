package com.example.finalproject.service;

import com.example.finalproject.domain.entity.instrument.Instrument;
import com.example.finalproject.domain.repository.InstrumentRepository;
import org.springframework.stereotype.Service;

@Service
public class InstrumentService {

    private final InstrumentRepository instrumentRepository;

    public InstrumentService(InstrumentRepository instrumentRepository){
        this.instrumentRepository = instrumentRepository;
    }

    public Instrument getInstrument(){
        return instrumentRepository.getInstrument();
    }

}
