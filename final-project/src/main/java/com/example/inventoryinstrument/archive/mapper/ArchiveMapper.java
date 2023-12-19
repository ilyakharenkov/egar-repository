package com.example.inventoryinstrument.archive.mapper;

import com.example.inventoryinstrument.archive.model.Archive;
import com.example.inventoryinstrument.archive.model.ArchiveDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ArchiveMapper {


    public ArchiveDto convertToDto(Archive archive){
        return ArchiveDto.builder()
                .id(archive.getId())
                .createArchive(archive.getCreateArchive())
                .deleteArchive(archive.getDeleteArchive())
                .idClient(archive.getIdClient())
                .nameClient(archive.getNameClient())
                .surnameClient(archive.getSurnameClient())
                .idInstrument(archive.getIdInstrument())
                .nameInstrument(archive.getNameInstrument())
                .startRental(archive.getStartRental())
                .endRental(archive.getEndRental())
                .dayRent(archive.getDayRent())
                .build();
    }

}
