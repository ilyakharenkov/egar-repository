package com.example.inventoryinstrument.client.service;

import com.example.inventoryinstrument.client.model.Client;
import com.example.inventoryinstrument.client.model.Role;
import com.example.inventoryinstrument.client.model.UserSecurity;
import com.example.inventoryinstrument.client.repository.RoleRepository;
import com.example.inventoryinstrument.client.repository.UserSecurityRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Configuration
@AllArgsConstructor
public class UserSecurityService implements UserDetailsService {
    private final UserSecurityRepository userSecurityRepository;
    private final RoleRepository roleRepository;


    //Поиск имени авторизированного пользователя.
    public UserSecurity findByPrincipal(Principal principal) {
        if (principal == null) {
            return new UserSecurity();
        }
        return findByUsername(principal.getName());
    }

    //Есть ли у авторизированного пользователя роль админа.
    public Boolean findByRoleAdmin(Principal principal) {
        var us = findByPrincipal(principal);
        for (Role role : us.getRoles()) {
            if (role.getName().equals("ROLE_ADMIN")) {
                return true;
            }
        }
        return false;
    }

    //Поиск существующих клиентов по имени.
    public UserSecurity findByUsername(String username) {
        return userSecurityRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException(String.format("User '%s' not found", username))
        );
    }

    //Добавление нового клиента (Регистрация).
    public void saveUserSecurity(UserSecurity userSecurity, Client client) {
        var us = UserSecurity.builder()
                .username(userSecurity.getUsername())
                .password(new BCryptPasswordEncoder().encode(userSecurity.getPassword()))
                .client(client)
                .roles(Set.of(roleRepository.findByName("ROLE_USER")
                        .orElseThrow(() -> new UsernameNotFoundException("Not found ROLE_USER"))))
                .build();
        userSecurityRepository.save(us);
    }

    //Мапа для SpringSecurity.
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var us = findByUsername(username);
        return User.builder()
                .username(us.getUsername())
                .password(us.getPassword())
                .authorities(us.getRoles().stream().map(
                        role -> new SimpleGrantedAuthority(role.getName())
                ).collect(Collectors.toList()))
                .build();
    }

}
