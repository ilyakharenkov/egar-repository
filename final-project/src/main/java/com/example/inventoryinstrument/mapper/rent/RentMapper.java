package com.example.inventoryinstrument.mapper.rent;

import com.example.inventoryinstrument.domain.entity.archive.Archive;
import com.example.inventoryinstrument.domain.entity.client.Client;
import com.example.inventoryinstrument.domain.entity.rent.Rent;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class RentMapper {

    public Rent convertToUpdateRent(Rent oldRent, Rent newRent, Client client, Archive archive){
        oldRent.setDayRent(newRent.getDayRent());
        oldRent.setStartRental(LocalDate.now());
        oldRent.setCheckStatus(true);
        oldRent.setClient(client);
        oldRent.setArchive(archive);
        return oldRent;
    }

}
