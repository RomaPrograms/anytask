package com.bsu.famcs.anytask.validator;


import com.bsu.famcs.anytask.entity.Task;
import com.bsu.famcs.anytask.service.interfaces.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class TaskValidator implements Validator {

    private final TaskService taskService;

    @Autowired
    public TaskValidator(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Task.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Task task = (Task) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
        if (task.getName().length() < 3 || task.getName().length() > 100) {
            errors.rejectValue("name", "Size.task.name");
            if (taskService.findByName(task.getName()) != null) {
                errors.rejectValue("name", "Duplicate.task.name");
            }
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dueDate", "NotEmpty");
    }
}
