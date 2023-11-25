package com.example.finalproject.service.instrument;

import com.example.finalproject.domain.dto.instrument.AlignmentDto;
import com.example.finalproject.domain.dto.price.PriceDto;
import com.example.finalproject.domain.repository.instrument.AlignmentRepository;
import com.example.finalproject.mapping.instrument.AlignmentMapping;
import com.example.finalproject.service.price.PriceService;
import com.example.finalproject.service.rent.RentService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@EnableScheduling
@AllArgsConstructor
public class AlignmentService {
    private final AlignmentRepository alignmentRepository;
    private final AlignmentMapping alignmentMapping;
    private final PriceService priceService;

    private final RentService rentService;

    public List<AlignmentDto> findAll() {
        var alignmentDtoList = alignmentRepository.findAll()
                .stream()
                .map(alignmentMapping::convertToDto)
                .collect(Collectors.toList());
        priceService.executeResult(alignmentDtoList);
        return alignmentDtoList;
    }

    //Поправить Exception.
    public AlignmentDto getAlignmentById(Long id) {
        for (AlignmentDto alignmentDto : findAll()) {
            if (alignmentDto.getId().equals(id)) {
                return alignmentDto;
            }
        }
        return new AlignmentDto();
    }

    public void save(AlignmentDto alignmentDto, PriceDto priceDto) {
        alignmentDto.setPriceDto(priceDto);
        alignmentDto.setCheckStatus(true);
        var alignment = alignmentMapping.convertToEntity(alignmentDto);
        alignmentRepository.save(alignment);
    }

    public void deleteById(Long id) {
        alignmentRepository.deleteById(id);
    }

    //Изменение статуса инструмента при его аренде.
    public void checkStatus(AlignmentDto alignmentDto) {
        alignmentDto.setCheckStatus(false);
        update(alignmentDto);
    }

    //Поправить редактирование, данный способ работает если совпадет id сущности.
    public void update(AlignmentDto alignmentDto) {
        alignmentRepository.save(alignmentMapping.convertToEntity(alignmentDto));
    }

    //Время аренды закончилось.
    @Scheduled(fixedRate = 40_000)
    @Transactional
    public void timeOutRentAlignment() {
        rentService.findAll().forEach(rent -> {
//            if (rent.getEndRental().equals(LocalDate.now())) {
                System.out.println("Delete rent");
                rent.getAlignment().setCheckStatus(true);
                update(alignmentMapping.convertToDto(rent.getAlignment()));
                rentService.deleteById(rent.getId());
//            }
        });
    }
}
