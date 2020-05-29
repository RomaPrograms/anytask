package com.bsu.famcs.anytask.service.impls;

import com.bsu.famcs.anytask.entity.Task;
import com.bsu.famcs.anytask.repository.TaskRepository;
import com.bsu.famcs.anytask.service.interfaces.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task findByName(String name) {
        return taskRepository.findByName(name).orElse(null);
    }

    @Override
    public Task create(Task entity) {
        return taskRepository.saveAndFlush(entity);
    }

    @Override
    public Task save(Task task) {
        return taskRepository.saveAndFlush(task);
    }

    @Override
    public void update(Task entity) {
        taskRepository.saveAndFlush(entity);
    }

    @Override
    public void delete(Task entity) {
        taskRepository.delete(entity);
    }
}
