package com.example.inventoryinstrument.client.mapper;

import com.example.inventoryinstrument.client.model.Client;
import com.example.inventoryinstrument.client.model.ClientDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
@Builder
@AllArgsConstructor
public class ClientMapper {

    public ClientDto convertToDto(Client client){
        return ClientDto.builder()
                .id(client.getId())
                .firstName(client.getFirstName())
                .surname(client.getSurname())
                .build();
    }

}
