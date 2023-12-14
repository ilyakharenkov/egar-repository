package com.example.inventoryinstrument.domain.repository.renovation;

import com.example.inventoryinstrument.domain.entity.renovation.Renovation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RenovationRepository extends JpaRepository<Renovation, Long> {

    //������� ������������ ����������� �����������.
    @Query(value = "SELECT * FROM renovation WHERE renovation.alignment_id = :id", nativeQuery = true)
    Optional<Renovation> findRenovationByAlignmentId(@Param("id") Long id);

    @Query(value = "SELECT * FROM renovation WHERE renovation.countersink_id = :id", nativeQuery = true)
    Optional<Renovation> findRenovationByCountersinkId(@Param("id") Long id);

}