package com.example.finalproject.service.renovation;

import com.example.finalproject.domain.repository.renovation.RenovationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RenovationService {

    private final RenovationRepository renovationRepository;

}
