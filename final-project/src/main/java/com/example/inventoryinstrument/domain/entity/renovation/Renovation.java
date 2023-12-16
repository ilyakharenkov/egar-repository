package com.example.inventoryinstrument.domain.entity.renovation;

import com.example.inventoryinstrument.domain.entity.instrument.Alignment;
import com.example.inventoryinstrument.domain.entity.instrument.Countersink;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


// Ремонт инструмента.
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Renovation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Дата начала диагностики.
    @Column
    @NotNull(message = "startRenovation не должно равняться null")
    private LocalDate startRenovation;

    //Дата окончания диагностики.
    @Column
    @NotNull(message = "endRenovation не должно равняться null")
    private LocalDate endRenovation;

    //Колличество дней на диагностику.
    @Column
    @NotNull(message = "countDay не должно равняться null")
    @Min(value = 1, message = "Минимум дней обслуживания 1")
    private Integer countDay;

    //Затраты на диагностику.
    @Column
    @NotNull(message = "priceDiagnostics не должно равняться null")
    private Double priceDiagnostics;

    //Информация о результатах диагностики.
    @Column
    @NotNull(message = "descriptionResult не должно равняться null")
    private String descriptionResult;

    //Затраты на обслуживание.
    @Column
    @NotNull(message = "resultPrice не должно равняться null")
    private Double resultPrice;

    //Статус ремонта, завершен или нет.
    @Column
    private Boolean checkStatus;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private Alignment alignment;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private Countersink countersink;

}
