package com.bsu.famcs.anytask.service.interfaces;


import com.bsu.famcs.anytask.entity.Task;

public interface TaskService {

    Task findByName(String name);

    Task create(Task entity);

    Task save(Task task);

    void update(Task entity);

    void delete(Task entity);
}
