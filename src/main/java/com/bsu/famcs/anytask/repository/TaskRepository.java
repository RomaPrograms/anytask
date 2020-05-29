package com.bsu.famcs.anytask.repository;

import com.bsu.famcs.anytask.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    Optional<Task> findByName(String name);
}
