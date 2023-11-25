package com.example.finalproject.domain.dto.client;

import com.example.finalproject.domain.entity.client.Client;
import com.example.finalproject.domain.entity.client.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSecurityDto {

    private Long id;

    private String username;

    private String password;

    private Set<Role> roles = new HashSet<>();

    private ClientDto clientDto;

}
