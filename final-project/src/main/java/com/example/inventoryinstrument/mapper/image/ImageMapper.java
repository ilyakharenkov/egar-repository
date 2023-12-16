package com.example.inventoryinstrument.mapper.image;

import com.example.inventoryinstrument.domain.dto.image.ImageDto;
import com.example.inventoryinstrument.domain.entity.image.Image;
import com.example.inventoryinstrument.mapper.instrument.AlignmentMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ImageMapper {

    public ImageDto convertToDto(Image image){
        return ImageDto.builder()
                .id(image.getId())
                .name(image.getName())
                .downloadLink(image.getDownloadLink())
                .build();
    }

    public Image convertToEntity(ImageDto imageDto){
        return Image.builder()
                .id(imageDto.getId())
                .name(imageDto.getName())
                .downloadLink(imageDto.getDownloadLink())
                .build();
    }

}
