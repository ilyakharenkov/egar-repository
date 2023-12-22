package com.example.inventoryinstrument.image.model;

import com.example.inventoryinstrument.alignment.model.Alignment;
import com.example.inventoryinstrument.countersink.model.Countersink;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Lob
    @Column
    private Blob blob;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Alignment alignment;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Countersink countersink;

}
