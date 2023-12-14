package com.example.inventoryinstrument.domain.repository.rent;

import com.example.inventoryinstrument.domain.entity.rent.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RentRepository extends JpaRepository<Rent, Long> {

    //Выбрать существующую арену по id инструмента.
    @Query(value = "SELECT * FROM rent WHERE rent.alignment_id = :id", nativeQuery = true)
    Optional<Rent> findRentByAlignmentId(@Param("id") Long id);

    //Выбрать существующую арену по id инструмента.
    @Query(value = "SELECT * FROM rent WHERE rent.countersink_id = :id", nativeQuery = true)
    Optional<Rent> findRentByCountersinkId(@Param("id") Long id);

    //Показать весь арендованный инструмент.
    @Query("SELECT r FROM Rent r WHERE r.checkStatus = true")
    List<Rent> findAllRent();

    //Показать весь свободный инструмент который когда то был арендован.
    @Query("SELECT r FROM Rent r WHERE r.checkStatus = false")
    List<Rent> findAllFree();

    //Показать пользователю какой у него инструмент в аренде.
    @Query("SELECT r FROM Rent r WHERE r.client.id = :id AND r.checkStatus = true")
    List<Rent> findRentByClient(@Param("id") Long id);

    //Сортировка по началу аренды.
    @Query("SELECT r FROM Rent r WHERE r.checkStatus = true ORDER BY r.startRental")
    List<Rent> sortByStartRental();

    //Сортировка по окончанию аренды.
    @Query("SELECT r FROM Rent r WHERE r.checkStatus = true ORDER BY r.endRental")
    List<Rent> sortByEndRental();

}
