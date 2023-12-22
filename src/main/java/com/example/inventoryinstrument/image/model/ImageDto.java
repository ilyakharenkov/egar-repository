package com.example.inventoryinstrument.image.model;

import com.example.inventoryinstrument.alignment.model.AlignmentDto;
import com.example.inventoryinstrument.countersink.model.CountersinkDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageDto {

        private Long id;

        private String name;

        private Blob blob;

        private AlignmentDto alignmentDto;

        private CountersinkDto countersinkDto;

}
