package com.example.inventoryinstrument.domain.dto.image;

import com.example.inventoryinstrument.domain.dto.instrument.AlignmentDto;
import com.example.inventoryinstrument.domain.dto.instrument.CountersinkDto;
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
