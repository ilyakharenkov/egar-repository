package com.example.inventoryinstrument.image.model;

import com.example.inventoryinstrument.alignment.model.Alignment;
import com.example.inventoryinstrument.countersink.model.Countersink;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



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

    //Путь к файлу.
    @Column
    private String downloadLink;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Alignment alignment;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Countersink countersink;

}
