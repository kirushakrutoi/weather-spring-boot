package ru.kirill.WhetherSpringBoot.validations;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.executable.ExecutableValidator;
import jakarta.validation.metadata.BeanDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kirill.WhetherSpringBoot.models.User;
import ru.kirill.WhetherSpringBoot.repositories.UserRepository;
import ru.kirill.WhetherSpringBoot.security.UserDetailsImpl;
import ru.kirill.WhetherSpringBoot.services.UserDetailsServiceImpl;

import java.util.Set;

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

        try {
            userDetailsService.loadUserByUsername(user.getEmail());
        } catch (UsernameNotFoundException ignored){
            return;
        }

        errors.rejectValue("email", "403", "Человек с таким email уже существует");
    }
}
