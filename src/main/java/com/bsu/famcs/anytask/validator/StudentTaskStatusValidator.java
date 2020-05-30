package com.bsu.famcs.anytask.validator;

import com.bsu.famcs.anytask.entity.StudentTaskStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class StudentTaskStatusValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return StudentTaskStatus.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        StudentTaskStatus task = (StudentTaskStatus) o;
        if (task.getMark() < 0) {
            errors.rejectValue("mark", "Negative.task.mark");
        }
        if (task.getMark() > 10) {
            errors.rejectValue("mark", "Invalid.task.mark");
        }

//        Pattern pattern = Pattern.compile("[0-10]\\d*");
//        Matcher matchDates = pattern.matcher(Integer.toString(task.getMark()));
//        if (!matchDates.matches()) {
//            errors.rejectValue("mark", "Invalid.task.naturalMark");
//        }
    }
}

