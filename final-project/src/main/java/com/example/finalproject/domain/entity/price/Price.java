package com.example.finalproject.domain.entity.price;

import com.example.finalproject.domain.entity.instrument.Alignment;
import jakarta.persistence.*;
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
    private Double purchase;

    //Расходы на реализацию (Например заработная плата сотрудникам и аренда).
    @Column
    private Double expenses;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "price")
    private Alignment alignment;

}
