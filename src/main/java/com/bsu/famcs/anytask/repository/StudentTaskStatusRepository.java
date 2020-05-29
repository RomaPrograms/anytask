package com.bsu.famcs.anytask.repository;

import com.bsu.famcs.anytask.entity.Label;
import com.bsu.famcs.anytask.entity.StudentTaskStatus;
import com.bsu.famcs.anytask.entity.Task;
import com.bsu.famcs.anytask.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentTaskStatusRepository extends JpaRepository<StudentTaskStatus, Integer> {
    Optional<StudentTaskStatus> findStudentTaskStatusByLabelAndTaskAndStudent(Label label, Task task, User user);
}
