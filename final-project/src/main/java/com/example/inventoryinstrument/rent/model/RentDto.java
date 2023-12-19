package com.example.inventoryinstrument.rent.model;

import com.example.inventoryinstrument.alignment.model.AlignmentDto;
import com.example.inventoryinstrument.archive.model.ArchiveDto;
import com.example.inventoryinstrument.client.model.ClientDto;
import com.example.inventoryinstrument.countersink.model.CountersinkDto;
import com.example.inventoryinstrument.profit.model.ProfitDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentDto {

    private Long id;

    //Начало аренды.
    private LocalDate startRental;

    //Окончание аренды.
    private LocalDate endRental;

    //Колличество дней аренды.
    private Integer dayRent;

    //Статус аренды.
    private Boolean checkStatus;

    private ClientDto client;

    private AlignmentDto alignment;

    private CountersinkDto countersink;

    private List<ProfitDto> profit;

    private ArchiveDto archive;

}
