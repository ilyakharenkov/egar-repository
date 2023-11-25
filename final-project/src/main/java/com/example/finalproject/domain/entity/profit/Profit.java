package com.example.finalproject.domain.entity.profit;

import com.example.finalproject.domain.entity.rent.Rent;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Profit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Double income;

    @OneToOne(mappedBy = "profit")
    private Rent rent;
}
