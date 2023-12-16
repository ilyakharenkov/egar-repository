package com.example.inventoryinstrument.client.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

//Аккаунт.
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSecurity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Логин.
    @Column
    @NotNull
    private String username;

    //Пароль.
    @Column
    @NotNull
    private String password;

    @ManyToMany
    @JoinTable(
            name = "user_security_role",
            joinColumns = @JoinColumn(name = "user_security_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Client client;


}
