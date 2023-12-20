package com.example.inventoryinstrument.client.model;

import com.example.inventoryinstrument.rent.model.RentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {

    private Long id;

    private String firstName;

    private String surname;


    private List<RentDto> rentList = new ArrayList<>();

}
