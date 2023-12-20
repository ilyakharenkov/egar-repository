package com.example.inventoryinstrument.rent.model;

import com.example.inventoryinstrument.archive.model.Archive;
import com.example.inventoryinstrument.client.model.Client;
import com.example.inventoryinstrument.alignment.model.Alignment;
import com.example.inventoryinstrument.countersink.model.Countersink;
import com.example.inventoryinstrument.profit.model.Profit;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

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

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private Alignment alignment;

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private Countersink countersink;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "rent")
    private List<Profit> profit;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Archive archive;

}
