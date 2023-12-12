package com.example.finalproject.service.rent;

import com.example.finalproject.domain.entity.rent.Rent;
import com.example.finalproject.domain.repository.rent.RentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class RentService {

    private final RentRepository rentRepository;

    //Спсиок всей аренды.
    public List<Rent> findAll() {
        return rentRepository.findAll(Sort.by(Sort.Direction.ASC, "dayRent"));
    }

    //Список арендованного инструмента.
    public List<Rent> findAllRent() {
        return rentRepository.findAllRent();
    }

    //Найти существующую аренду инструмента если такая была.
    public Rent findRentByAlignmentId(Long id) {
        return rentRepository.findRentByAlignmentId(id)
                .orElse(null);
    }

    //Найти существующую аренду инструмента если такая была.
    public Rent findRentByCountersinkId(Long id) {
        return rentRepository.findRentByCountersinkId(id)
                .orElse(null);
    }

    //Показать пользователю какой у него инструмент в аренде.
    public List<Rent> findRentByClient(Long id) {
        return rentRepository.findRentByClient(id);
    }

    //Список освободившегося инструмента.
    public List<Rent> findAllFree() {
        return rentRepository.findAllFree();
    }

    //Сохранение информации об аренде.
    public void save(Rent rent) {
        rentRepository.save(rent);
    }

    //Расчет когда время аренды вышло.
    public LocalDate timeOutRent(LocalDate localDate, Integer day) {
        return localDate.plusDays(day);
    }

    public void deleteById(Long id) {
        rentRepository.deleteById(id);
    }

    public void update(Rent rent) {
        rentRepository.save(rent);
    }

    public List<Rent> sortByStartRental() {
        return rentRepository.sortByStartRental();
    }

    public List<Rent> sortByEndRental() {
        return rentRepository.sortByEndRental();
    }

    //Время аренды закончилось.
    @Scheduled(cron = "0 0 3 * * *", zone = "Europe/Moscow")
    @Transactional
    public void timeOutRent() {
        findAll().forEach(rent -> {
            if (rent != null) {
                if (rent.getCheckStatus()) {
                    if (rent.getEndRental().compareTo(LocalDate.now()) <= 0) {
                        rent.setCheckStatus(false);
                        this.update(rent);
                    }
                }
            }
        });
    }


}
