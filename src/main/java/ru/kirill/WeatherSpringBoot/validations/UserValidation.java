package ru.kirill.WeatherSpringBoot.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kirill.WeatherSpringBoot.models.User;
import ru.kirill.WeatherSpringBoot.services.UserDetailsServiceImpl;

@Component
public class UserValidation implements Validator {

    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    public UserValidation(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        System.out.println("1234512341234");

        try {
            userDetailsService.loadUserByUsername(user.getEmail());
        } catch (UsernameNotFoundException ignored){
            return;
        }

        errors.rejectValue("email", "403", "Человек с таким email уже существует");
    }
}
