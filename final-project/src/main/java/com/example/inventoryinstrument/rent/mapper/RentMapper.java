package com.example.inventoryinstrument.rent.mapper;

import com.example.inventoryinstrument.alignment.mapper.AlignmentMapper;
import com.example.inventoryinstrument.archive.mapper.ArchiveMapper;
import com.example.inventoryinstrument.archive.model.Archive;
import com.example.inventoryinstrument.client.mapper.ClientMapper;
import com.example.inventoryinstrument.client.model.Client;
import com.example.inventoryinstrument.countersink.mapper.CountersinkMapper;
import com.example.inventoryinstrument.rent.model.Rent;
import com.example.inventoryinstrument.rent.model.RentDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class RentMapper {

    private final AlignmentMapper alignmentMapper;
    private final CountersinkMapper countersinkMapper;
    private final ArchiveMapper archiveMapper;
    private final ClientMapper clientMapper;

    public RentDto convertToDto(Rent rent) {
        return validationNullableOfRent(rent);
    }

    public Rent convertToUpdateRent(Rent oldRent, Rent newRent, Client client, Archive archive){
        oldRent.setDayRent(newRent.getDayRent());
        oldRent.setStartRental(LocalDate.now());
        oldRent.setCheckStatus(true);
        oldRent.setClient(client);
        oldRent.setArchive(archive);
        return oldRent;
    }

    public RentDto validationNullableOfRent(Rent rent) {
        if (rent.getAlignment() != null) {
            return RentDto.builder()
                    .id(rent.getId())
                    .startRental(rent.getStartRental())
                    .endRental(rent.getEndRental())
                    .dayRent(rent.getDayRent())
                    .checkStatus(rent.getCheckStatus())
                    .client(clientMapper.convertToDto(rent.getClient()))
                    .alignment(alignmentMapper.convertToDto(rent.getAlignment(), rent.getAlignment().getPrice()))
                    .archive(archiveMapper.convertToDto(rent.getArchive()))
                    .build();
        }
        if (rent.getCountersink() != null) {
            return RentDto.builder()
                    .id(rent.getId())
                    .startRental(rent.getStartRental())
                    .endRental(rent.getEndRental())
                    .dayRent(rent.getDayRent())
                    .checkStatus(rent.getCheckStatus())
                    .client(clientMapper.convertToDto(rent.getClient()))
                    .countersink(countersinkMapper.convertToDto(rent.getCountersink(), rent.getCountersink().getPrice()))
                    .archive(archiveMapper.convertToDto(rent.getArchive()))
                    .build();
        }
        return RentDto.builder().build();
    }

}
