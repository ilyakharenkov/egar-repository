package com.example.finalproject.domain.repository.profit;

import com.example.finalproject.domain.entity.profit.Profit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfitRepository extends JpaRepository<Profit, Long> {

}
