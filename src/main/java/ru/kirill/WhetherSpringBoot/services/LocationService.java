package ru.kirill.WhetherSpringBoot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kirill.WhetherSpringBoot.models.Location;
import ru.kirill.WhetherSpringBoot.models.User;
import ru.kirill.WhetherSpringBoot.repositories.LocationRepository;

import java.util.List;
import java.util.Optional;

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

    @Transactional
    public void delete(User user, String name){
        locationRepository.deleteByUserAndName(user, name);
    }

    @Transactional(readOnly = true)
    public Location findByName(String name){
        return locationRepository.findFirstByName(name);
    }

    @Transactional(readOnly = true)
    public Optional<Location> findByUserAndName(User user, String name){
        return locationRepository.findFirstByUserAndName(user, name);
    }
}
