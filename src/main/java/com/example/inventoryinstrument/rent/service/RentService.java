package com.example.inventoryinstrument.rent.service;

import com.example.inventoryinstrument.archive.model.Archive;
import com.example.inventoryinstrument.client.model.Client;
import com.example.inventoryinstrument.rent.mapper.RentMapper;
import com.example.inventoryinstrument.rent.model.Rent;
import com.example.inventoryinstrument.rent.model.RentDto;
import com.example.inventoryinstrument.rent.repository.RentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class RentService {

    private final RentRepository rentRepository;
    private final RentMapper rentMapper;

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
    public List<RentDto> findRentByClient(Long id) {
        return rentRepository.findRentByClient(id)
                .stream()
                .map(rentMapper::convertToDto)
                .collect(Collectors.toList());
    }

    //Список освободившегося инструмента.
    public List<RentDto> findAllFree() {
        return rentRepository.findAllFree()
                .stream()
                .map(rentMapper::convertToDto)
                .collect(Collectors.toList());
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

    public List<RentDto> sortByStartRental() {
        return rentRepository.sortByStartRental()
                .stream()
                .map(rentMapper::convertToDto)
                .collect(Collectors.toList());
    }

    public List<RentDto> sortByEndRental() {
        return rentRepository.sortByEndRental()
                .stream()
                .map(rentMapper::convertToDto)
                .collect(Collectors.toList());
    }

    //Создание экземпляра класса Rent.
    public Rent createObjectRent(Rent rent, Client client, Archive archive) {
        return Rent.builder()
                .dayRent(rent.getDayRent())
                .startRental(LocalDate.now())
                .endRental(timeOutRent(LocalDate.now(), rent.getDayRent()))
                .checkStatus(true)
                .client(client)
                .archive(archive)
                .build();
    }

    //Проверяем существовал ли такой объект или нет.
    //Если да то обновляем данные и возваращаем назад.
    //Если нет то создаем и возваращаем новый.
    public Rent validationOfRentForSave(Rent oldRent, Rent rent, Client client, Archive archive) {
        if (oldRent != null && !oldRent.getCheckStatus()) {

            //Перенес в маппер.
            var r = rentMapper.convertToUpdateRent(oldRent, rent, client, archive);
            r.setEndRental(this.timeOutRent(LocalDate.now(), rent.getDayRent()));
            return r;
        } else {
            return this.createObjectRent(rent, client, archive);
        }
    }

    //Время аренды закончилось.
    @Scheduled(cron = "0 0 3 * * *", zone = "Europe/Moscow")
    @Transactional
    public void timeOutRent() {
        findAll().forEach(rent -> {
            if (rent != null && rent.getCheckStatus() && rent.getEndRental().compareTo(LocalDate.now()) <= 0) {
                rent.setCheckStatus(false);
                this.update(rent);
                log.info(String.format("Время аренды %s закончилось", rent));
            }
        });
    }


}
