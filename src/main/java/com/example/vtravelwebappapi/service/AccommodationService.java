package com.example.vtravelwebappapi.service;

import com.example.vtravelwebappapi.model.Accommodation;
import com.example.vtravelwebappapi.repository.AccommodationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AccommodationService {

    @Autowired
    private AccommodationRepository accommodationRepository;

    public void saveAccommodation(Accommodation accommodation) {
        accommodationRepository.save(accommodation);
    }

    public Accommodation findAccommodationById(Long id) {
        return accommodationRepository.findById(id).orElse(null);
    }

    public List<Accommodation> getAllAccommodations() {
        return accommodationRepository.findAll();
    }

    // Additional methods for other operations on Accommodation entities...
}
