package com.example.inventoryinstrument.domain.entity.image;

import com.example.inventoryinstrument.domain.entity.instrument.Alignment;
import com.example.inventoryinstrument.domain.entity.instrument.Countersink;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


//Изображение.
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Имя файла.
    @Column
    private String name;

    //Ссылка на файл.
    @Column
    private String downloadLink;

    @ManyToOne(cascade = CascadeType.ALL)
    private Alignment alignment;

    @ManyToOne(cascade = CascadeType.ALL)
    private Countersink countersink;



}
