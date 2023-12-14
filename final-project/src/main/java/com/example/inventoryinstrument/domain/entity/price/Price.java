package com.example.inventoryinstrument.domain.entity.price;

import com.example.inventoryinstrument.domain.entity.instrument.Alignment;
import com.example.inventoryinstrument.domain.entity.instrument.Countersink;
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

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "price")
    private Alignment alignment;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "price")
    private Countersink countersink;

}
