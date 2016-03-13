package web.controllers.validator;

import domain.Student;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 */
public class StudentValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Student.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numbers", "required", "Numbers is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required", "Name is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required", "Password is required.");
    }
}
