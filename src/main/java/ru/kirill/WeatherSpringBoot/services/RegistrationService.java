package ru.kirill.WeatherSpringBoot.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kirill.WeatherSpringBoot.models.User;
import ru.kirill.WeatherSpringBoot.repositories.UserRepository;

@Service
public class RegistrationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public RegistrationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(User user){
        String password = passwordEncoder.encode(user.getPassword());

        user.setPassword(password);

        userRepository.save(user);
    }
}
