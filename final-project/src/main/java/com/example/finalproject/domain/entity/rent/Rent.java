package com.example.finalproject.domain.entity.rent;

import com.example.finalproject.domain.entity.archive.Archive;
import com.example.finalproject.domain.entity.client.Client;
import com.example.finalproject.domain.entity.instrument.Alignment;
import com.example.finalproject.domain.entity.profit.Profit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDate startRental;

    @Column
    private LocalDate endRental;

    @Column
    private Integer dayRent;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private Client client;

    @OneToOne(fetch = FetchType.LAZY)
    private Alignment alignment;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Profit profit;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Archive archive;

}
