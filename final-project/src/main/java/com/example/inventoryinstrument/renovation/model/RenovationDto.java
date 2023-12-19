package com.example.inventoryinstrument.renovation.model;

import com.example.inventoryinstrument.alignment.model.AlignmentDto;
import com.example.inventoryinstrument.countersink.model.CountersinkDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RenovationDto {

    private Long id;

    //Дата начала диагностики.
    private LocalDate startRenovation;

    //Дата окончания диагностики.
    private LocalDate endRenovation;

    //Колличество дней на диагностику.
    private Integer countDay;

    //Затраты на диагностику.
    private Double priceDiagnostics;

    //Информация о результатах диагностики.
    private String descriptionResult;

    //Затраты на обслуживание.
    private Double resultPrice;

    //Статус ремонта, завершен или нет.
    private Boolean checkStatus;

    private AlignmentDto alignment;

    private CountersinkDto countersink;

}
