package com.example.inventoryinstrument.archive.model;

import com.example.inventoryinstrument.rent.model.RentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArchiveDto {

    private Long id;

    //Создание архива.
    private LocalDate createArchive;

    //Удаление архива.
    private LocalDate deleteArchive;

    //Id клиента.
    private Long idClient;

    //Имя клиента.
    private String nameClient;

    //Фамилия клиента.
    private String surnameClient;

    //Id интсрумента.
    private Long idInstrument;

    //Имя инструмента.
    private String nameInstrument;

    //Начало аренды.
    private LocalDate startRental;

    //Конец аренды.
    private LocalDate endRental;

    //Дней в аренде.
    private Integer dayRent;


}

