package com.example.finalproject.domain.entity.instrument;

import com.example.finalproject.domain.entity.price.Price;
import com.example.finalproject.domain.entity.rent.Rent;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


// Центровка
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
    private String name;
    @Column
    private Integer diameter;
    @Column
    private Integer length;
    @Column
    private Integer workLength;
    @Column
    private Integer angle;
    @Column
    private Boolean checkStatus;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Price price;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "alignment")
    private Rent rent;

}
