package com.example.inventoryinstrument.alignment.repository;

import com.example.inventoryinstrument.alignment.model.Alignment;
import com.example.inventoryinstrument.price.model.Price;
import com.example.inventoryinstrument.rent.model.Rent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
public class AlignmentRepositoryTests {

    @Mock
    AlignmentRepository alignmentRepository;

    @Test
    void findAll_findListAlignment() {

        //Что дано
        var alignmentList = List.of(
                new Alignment(1L, "Alignment 11", 5, 40, 10, 120, true, new Price(), new Rent(), new ArrayList<>(), new ArrayList<>()),
                new Alignment(1L, "Alignment 12", 5, 40, 10, 120, false, new Price(), new Rent(), new ArrayList<>(), new ArrayList<>()),
                new Alignment(1L, "Alignment 13", 5, 40, 10, 120, true, new Price(), new Rent(), new ArrayList<>(), new ArrayList<>())
        );
        Mockito.doReturn(alignmentList).when(this.alignmentRepository).findAll();

        //Кем используется.
        var alignmentDtoList = this.alignmentRepository.findAll();

        //Что проверяется.
        Assertions.assertNotNull(alignmentDtoList);
    }

}
