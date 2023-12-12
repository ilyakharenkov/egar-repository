package com.example.finalproject.domain.entity.image;

import com.example.finalproject.domain.entity.instrument.Alignment;
import com.example.finalproject.domain.entity.instrument.Countersink;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.css.Counter;


//�����������.
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //��� �����.
    @Column
    private String name;

    //������ �� ����.
    @Column
    private String downloadLink;

    @ManyToOne(cascade = CascadeType.ALL)
    private Alignment alignment;

    @ManyToOne(cascade = CascadeType.ALL)
    private Countersink countersink;



}
