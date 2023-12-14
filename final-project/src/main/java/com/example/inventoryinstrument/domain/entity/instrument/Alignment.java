package com.example.inventoryinstrument.domain.entity.instrument;

import com.example.inventoryinstrument.domain.entity.image.Image;
import com.example.inventoryinstrument.domain.entity.price.Price;
import com.example.inventoryinstrument.domain.entity.renovation.Renovation;
import com.example.inventoryinstrument.domain.entity.rent.Rent;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

// Инструмент центровка.
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Alignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull(message = "name не может быть null")
    private String name;

    @Column
    @NotNull(message = "diameter не может быть null")
    @Min(value = 1, message = "Min diameter 1")
    private Integer diameter;

    @Column
    @NotNull(message = "length не может быть null")
    @Min(value = 10, message = "Min length 10")
    private Integer length;

    @Column
    @NotNull(message = "workLength не может быть null")
    @Min(value = 2, message = "Min workLength 2")
    private Integer workLength;

    @Column
    @NotNull(message = "angle не может быть null")
    @Min(value = 90, message = "Min angle 90")
    @Max(value = 120, message = "Max angle 120")
    private Integer angle;

    @Column
    @NotNull(message = "checkStatus не может быть null")
    private Boolean checkStatus;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @NotNull
    private Price price;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "alignment")
    private Rent rent;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "alignment")
    private List<Image> imageList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "alignment")
    private List<Renovation> renovation = new ArrayList<>();

}
