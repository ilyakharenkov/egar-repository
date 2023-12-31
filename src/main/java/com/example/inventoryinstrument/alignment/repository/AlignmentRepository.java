package com.example.inventoryinstrument.alignment.repository;


import com.example.inventoryinstrument.alignment.model.Alignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlignmentRepository extends JpaRepository<Alignment, Long> {

    //Показать свободный инструмент.
    @Query("SELECT a FROM Alignment a WHERE a.checkStatus = true")
    List<Alignment> findFreeAlignment();

}
