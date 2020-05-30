package com.bsu.famcs.anytask.validator;


import com.bsu.famcs.anytask.entity.User;
import com.bsu.famcs.anytask.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    private final UserService userService;

    @Autowired
    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (user.getUsername().length() <= 1) {
            errors.rejectValue("username", "Size.userForm.login");
        }
        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.login");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 5 || user.getPassword().length() > 50) {
            errors.rejectValue("password", "Size.userForm.password");
        }
        if (!user.getPassword().equals(user.getPasswordConfirm())) {
            errors.rejectValue("password", "Diff.userForm.password");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordConfirm", "NotEmpty");
    }
}