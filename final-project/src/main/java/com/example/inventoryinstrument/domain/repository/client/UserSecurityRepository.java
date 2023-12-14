package com.example.inventoryinstrument.domain.repository.client;

import com.example.inventoryinstrument.domain.entity.client.UserSecurity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserSecurityRepository extends JpaRepository<UserSecurity, Long> {

    Optional<UserSecurity> findByUsername(String username);

}
