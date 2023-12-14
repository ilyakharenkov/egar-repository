package com.example.inventoryinstrument.domain.entity.rent;

import com.example.inventoryinstrument.domain.entity.archive.Archive;
import com.example.inventoryinstrument.domain.entity.client.Client;
import com.example.inventoryinstrument.domain.entity.instrument.Alignment;
import com.example.inventoryinstrument.domain.entity.instrument.Countersink;
import com.example.inventoryinstrument.domain.entity.profit.Profit;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

//Аренда.
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Начало аренды.
    @Column
    @NotNull
    private LocalDate startRental;

    //Окончание аренды.
    @Column
    @NotNull
    private LocalDate endRental;

    //Колличество дней аренды.
    @Column
    @NotNull(message = "dayRent не может быть Null")
    @Min(value = 1, message = "Минимум 1 день аренды")
    private Integer dayRent;

    //Статус аренды.
    @Column
    private Boolean checkStatus;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private Client client;

    @OneToOne(fetch = FetchType.LAZY)
    private Alignment alignment;

    @OneToOne(fetch = FetchType.LAZY)
    private Countersink countersink;

    @OneToOne(cascade = CascadeType.REFRESH, mappedBy = "rent")
    private Profit profit;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Archive archive;

}
