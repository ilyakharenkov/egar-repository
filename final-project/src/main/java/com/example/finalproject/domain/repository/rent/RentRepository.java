package com.example.finalproject.domain.repository.rent;

import com.example.finalproject.domain.entity.rent.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentRepository extends JpaRepository<Rent, Long> {
}
