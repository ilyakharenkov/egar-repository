package com.example.inventoryinstrument.image.model;

import com.example.inventoryinstrument.alignment.model.AlignmentDto;
import com.example.inventoryinstrument.countersink.model.CountersinkDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageDto {

        private Long id;

        //Имя файла.
        private String name;

        //Путь к файлу.
        private String downloadLink;

        private AlignmentDto alignmentDto;

        private CountersinkDto countersinkDto;

}
