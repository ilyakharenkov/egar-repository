package com.example.inventoryinstrument.rent.mapper;

import com.example.inventoryinstrument.archive.model.Archive;
import com.example.inventoryinstrument.client.model.Client;
import com.example.inventoryinstrument.rent.model.Rent;
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
