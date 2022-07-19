package ru.fijirash.pp.spring_security.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.fijirash.pp.spring_security.models.User;
import ru.fijirash.pp.spring_security.security.UserDetailsServiceImpl;

@Component
public class UserValidator implements Validator {

    private final UserDetailsServiceImpl userService;

    @Autowired
    public UserValidator(UserDetailsServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
       try {
           userService.loadUserByUsername(user.getName());
       } catch (UsernameNotFoundException ignored) {
           return;
       }
       errors.rejectValue("name", "", "User in db yet");
    }
}
