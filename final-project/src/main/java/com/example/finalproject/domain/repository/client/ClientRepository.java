package com.example.finalproject.domain.repository.client;


import com.example.finalproject.domain.entity.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {


}
