package ru.kirill.WhetherSpringBoot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kirill.WhetherSpringBoot.models.Location;
import ru.kirill.WhetherSpringBoot.models.User;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
    public List<Location> findByUser(User user);
    public void deleteByUserAndName(User user, String name);
}
