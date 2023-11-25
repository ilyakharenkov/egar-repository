package com.example.finalproject.service.rent;

import com.example.finalproject.domain.dto.instrument.AlignmentDto;
import com.example.finalproject.domain.entity.archive.Archive;
import com.example.finalproject.domain.entity.profit.Profit;
import com.example.finalproject.domain.entity.rent.Rent;
import com.example.finalproject.domain.repository.rent.RentRepository;
import com.example.finalproject.mapping.instrument.AlignmentMapping;
import com.example.finalproject.service.archive.ArchiveService;
import com.example.finalproject.service.client.UserSecurityService;
import com.example.finalproject.service.profit.ProfitService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class RentService {
    private final RentRepository rentRepository;
    private final UserSecurityService userSecurityService;
    private final ProfitService profitService;
    private final AlignmentMapping alignmentMapping;

    private final ArchiveService archiveService;

    //Спсиок всей аренды.
    public List<Rent> findAll() {
        return rentRepository.findAll();
    }

    //Сохранение информации об аренде Alignment.
    public void save(Principal principal, Rent rent, AlignmentDto alignmentDto) {
        var us = userSecurityService.findByPrincipal(principal);
        var a = new Archive();
        var r = Rent.builder()
                .dayRent(rent.getDayRent())
                .startRental(LocalDate.now())
                .endRental(executeEnd(LocalDate.now(), rent.getDayRent()))
                .alignment(alignmentMapping.convertToEntity(alignmentDto))
                .client(us.getClient())
                .profit(Profit.builder().build())
                .archive(a)
                .build();
        rentRepository.save(r);
        profitService.save(r, alignmentDto);
        a.setCreateArchive(r.getStartRental());
        a.setDeleteArchive(r.getStartRental().plusYears(1));
        a.setIdClient(r.getClient().getId());
        a.setNameClient(r.getClient().getFirstName());
        a.setSurnameClient(r.getClient().getSurname());
        a.setIdInstrument(r.getAlignment().getId());
        a.setNameInstrument(r.getAlignment().getName());
        a.setStartRental(r.getStartRental());
        a.setEndRental(r.getEndRental());
        a.setDayRent(r.getDayRent());
        archiveService.save(a);
    }

    //Расчет когда нужно отдать инструмент.
    private LocalDate executeEnd(LocalDate localDate, Integer day) {
        return localDate.plusDays(day);
    }

    public void deleteById(Long id) {
        rentRepository.deleteById(id);
    }

    public void update(Rent rent) {
        rentRepository.save(rent);
    }


}
