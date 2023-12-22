package com.example.inventoryinstrument.image.mapper;

import com.example.inventoryinstrument.image.model.ImageDto;
import com.example.inventoryinstrument.image.model.Image;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ImageMapper {

    public ImageDto convertToDto(Image image){
        return ImageDto.builder()
                .id(image.getId())
                .name(image.getName())
                .blob(image.getBlob())
                .build();
    }

    public Image convertToEntity(ImageDto imageDto){
        return Image.builder()
                .id(imageDto.getId())
                .name(imageDto.getName())
                .blob(imageDto.getBlob())
                .build();
    }

}
