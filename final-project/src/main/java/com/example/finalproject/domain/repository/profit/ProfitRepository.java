package com.example.finalproject.domain.repository.profit;

import com.example.finalproject.domain.entity.profit.Profit;
import com.example.finalproject.domain.entity.rent.Rent;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfitRepository extends JpaRepository<Profit, Long> {

}
