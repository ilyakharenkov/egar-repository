package com.example.finalproject.domain.repository.instrument;


import com.example.finalproject.domain.entity.instrument.Alignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlignmentRepository extends JpaRepository<Alignment, Long> {

}
