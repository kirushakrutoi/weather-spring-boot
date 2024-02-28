package ru.kirill.WhetherSpringBoot.services;

import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kirill.WhetherSpringBoot.exceptions.UserNotCreatedException;
import ru.kirill.WhetherSpringBoot.models.User;
import ru.kirill.WhetherSpringBoot.repositories.UserRepository;

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

        try {
            userRepository.save(user);
        } catch (DataAccessException e){
            throw new UserNotCreatedException("This email is exist");
        }
    }
}
