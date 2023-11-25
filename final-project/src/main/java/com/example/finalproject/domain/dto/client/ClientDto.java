package com.example.finalproject.domain.dto.client;

import com.example.finalproject.domain.entity.client.UserSecurity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {

    private Long id;

    private String firstName;

    private String surname;

    private Integer tabNumber;

    private LocalDateTime took;

    private LocalDateTime gave;

    private UserSecurityDto userSecurityDto;

}
