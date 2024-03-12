package ru.kirill.WeatherSpringBoot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kirill.WeatherSpringBoot.models.User;
import ru.kirill.WeatherSpringBoot.repositories.UserRepository;
import ru.kirill.WeatherSpringBoot.security.UserDetailsImpl;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> OUser = userRepository.findByEmail(username);

        if(OUser.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }

        return new UserDetailsImpl(OUser.get());
    }
}
