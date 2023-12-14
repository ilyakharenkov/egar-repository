package com.example.inventoryinstrument.domain.repository.client;

import com.example.inventoryinstrument.domain.entity.client.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);

}
