package com.example.inventoryinstrument.archive.model;

import com.example.inventoryinstrument.rent.model.Rent;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

//Архив.
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Archive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Создание архива.
    @Column
    private LocalDate createArchive;

    //Удаление архива.
    @Column
    private LocalDate deleteArchive;

    //Id клиента.
    @Column
    private Long idClient;

    //Имя клиента.
    @Column
    private String nameClient;

    //Фамилия клиента.
    @Column
    private String surnameClient;

    //Id интсрумента.
    @Column
    private Long idInstrument;

    //Имя инструмента.
    @Column
    private String nameInstrument;

    //Начало аренды.
    @Column
    private LocalDate startRental;

    //Конец аренды.
    @Column
    private LocalDate endRental;

    //Дней в аренде.
    @Column
    private Integer dayRent;

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "archive")
    private Rent rent;

}
