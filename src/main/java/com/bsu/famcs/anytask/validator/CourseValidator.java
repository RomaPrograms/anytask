package com.bsu.famcs.anytask.validator;

import com.bsu.famcs.anytask.entity.Course;
import com.bsu.famcs.anytask.entity.User;
import com.bsu.famcs.anytask.service.interfaces.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CourseValidator implements Validator {
    private final CourseService courseService;

    @Autowired
    public CourseValidator(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Course course = (Course) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
        if (course.getName().length() < 3 || course.getName().length() > 100) {
            errors.rejectValue("name", "Size.course.name");
        }
        if (courseService.findByName(course.getName()) != null) {
            errors.rejectValue("name", "Duplicate.course.name");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "NotEmpty");
        if (course.getDescription().length() < 1 || course.getName().length() > 255) {
            errors.rejectValue("description", "Size.course.description");
        }
    }
}