package com.example.inventoryinstrument.domain.entity.instrument;

import com.example.inventoryinstrument.domain.entity.image.Image;
import com.example.inventoryinstrument.domain.entity.price.Price;
import com.example.inventoryinstrument.domain.entity.renovation.Renovation;
import com.example.inventoryinstrument.domain.entity.rent.Rent;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

//Инструмент зенковка
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Countersink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    private String name;

    @Column
    @NotNull
    private Integer diameter;

    @Column
    @NotNull
    private Integer length;

    @Column
    @NotNull
    private Integer workLength;

    @Column
    @NotNull
    private Integer angle;

    @Column
    @NotNull
    private Boolean checkStatus;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Price price;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "countersink")
    private Rent rent;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "countersink")
    private List<Image> imageList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "countersink")
    private List<Renovation> renovation = new ArrayList<>();

}
