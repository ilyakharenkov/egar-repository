package com.example.finalproject.domain.entity.client;

import com.example.finalproject.domain.entity.rent.Rent;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


//Клиент.
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    private String firstName;

    @Column
    @NotNull
    private String surname;

//    @OneToOne(cascade = CascadeType.ALL)
//    private Profile profile;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "client")
    private List<Rent> rentList = new ArrayList<>();

    @OneToOne(mappedBy = "client")
    private UserSecurity userSecurity;

}
