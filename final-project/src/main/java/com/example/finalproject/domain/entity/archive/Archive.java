package com.example.finalproject.domain.entity.archive;

import com.example.finalproject.domain.entity.rent.Rent;
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

    @Column
    private LocalDate createArchive;

    @Column
    private LocalDate deleteArchive;

    @Column
    private Long idClient;

    @Column
    private String nameClient;

    @Column
    private String surnameClient;

    @Column
    private Long idInstrument;

    @Column
    private String nameInstrument;

    @Column
    private LocalDate startRental;

    @Column
    private LocalDate endRental;

    @Column
    private Integer dayRent;

    @OneToOne(mappedBy = "archive")
    private Rent rent;


}
