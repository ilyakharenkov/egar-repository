package com.example.finalproject.domain.repository.profit;

import com.example.finalproject.domain.entity.profit.Profit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfitRepository extends JpaRepository<Profit, Long> {

    //Сумма прибыли за 7 дней.
    @Query(value = "SELECT sum(profit.income) " +
            "FROM profit" +
            " WHERE (SELECT rent.start_rental FROM rent WHERE profit.rent_id = rent.id) > (current_date - INTERVAL '7 days') " +
            "AND (SELECT rent.start_rental FROM rent WHERE profit.rent_id = rent.id) < current_date", nativeQuery = true)
    Optional<Integer> findSumProfit();

}
