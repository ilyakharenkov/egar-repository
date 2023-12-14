package com.example.inventoryinstrument.domain.repository.instrument;

import com.example.inventoryinstrument.domain.entity.instrument.Countersink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountersinkRepository extends JpaRepository<Countersink, Long> {

    //Показать свободный инструмент.
    @Query("SELECT c FROM Countersink c WHERE c.checkStatus = true")
    List<Countersink> findFreeCountersink();
}
