package ru.kirill.WeatherSpringBoot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kirill.WeatherSpringBoot.models.Location;
import ru.kirill.WeatherSpringBoot.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
    public List<Location> findByUser(User user);
    public void deleteByUserAndName(User user, String name);
    public Location findFirstByName(String name);
    public Optional<Location> findFirstByUserAndName(User user, String name);
}
