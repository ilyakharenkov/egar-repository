package com.example.inventoryinstrument.price.model;

import com.example.inventoryinstrument.alignment.model.Alignment;
import com.example.inventoryinstrument.countersink.model.Countersink;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//Полная себестоимость продукции = затраты на производство или приобретение товара + расходы на реализацию товара
//Наценка = ((Цена - Себестоимость) / Себестоимость) * 100%
//Наценка способ 2 = ((Планируемая выручка / Издержки) - 1) * 100%
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Закупочная цена у производителя.
    @Column
    @NotNull
    private Double purchase;

    //Расходы на реализацию (Например заработная плата сотрудникам и аренда).
    @Column
    @NotNull
    private Double expenses;

    //Наценка.
    @Column
    @NotNull
    private Integer markup;

    //Цена аренды одного дня.
    @Column
    @NotNull
    private Double priceRentOfDay;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "price")
    private Alignment alignment;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "price")
    private Countersink countersink;

}
