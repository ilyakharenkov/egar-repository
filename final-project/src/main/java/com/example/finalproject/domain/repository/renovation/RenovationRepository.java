package com.example.finalproject.domain.repository.renovation;

import com.example.finalproject.domain.entity.renovation.Renovation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RenovationRepository extends JpaRepository<Renovation, Long> {
}
