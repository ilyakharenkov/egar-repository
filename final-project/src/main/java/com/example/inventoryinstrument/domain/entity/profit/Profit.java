package com.example.inventoryinstrument.domain.entity.profit;

import com.example.inventoryinstrument.domain.entity.rent.Rent;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//Выгода.
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Profit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Дней аренды.
    @Column
    @NotNull
    private Integer dayRent;

    //Сумма выручки с аренды.
    @Column
    @NotNull
    private Double income;

    //Сумма налога.
    @Column
    @NotNull
    private Double tax;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Rent rent;

}
