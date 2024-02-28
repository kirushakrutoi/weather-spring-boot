package ru.kirill.WhetherSpringBoot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kirill.WhetherSpringBoot.models.Location;
import ru.kirill.WhetherSpringBoot.models.User;
import ru.kirill.WhetherSpringBoot.repositories.LocationRepository;

import java.util.List;

@Service
public class LocationService {
    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Transactional
    public void save(Location location){
        locationRepository.save(location);
    }

    @Transactional(readOnly = true)
    public List<Location> findByUser(User user){
        return locationRepository.findByUser(user);
    }
}
