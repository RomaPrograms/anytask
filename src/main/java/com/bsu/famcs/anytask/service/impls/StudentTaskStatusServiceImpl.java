package com.bsu.famcs.anytask.service.impls;

import com.bsu.famcs.anytask.entity.Label;
import com.bsu.famcs.anytask.entity.StudentTaskStatus;
import com.bsu.famcs.anytask.entity.Task;
import com.bsu.famcs.anytask.entity.User;
import com.bsu.famcs.anytask.repository.StudentTaskStatusRepository;
import com.bsu.famcs.anytask.service.interfaces.StudentTaskStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentTaskStatusServiceImpl implements StudentTaskStatusService {

    private final StudentTaskStatusRepository studentTaskStatusRepository;

    @Autowired
    public StudentTaskStatusServiceImpl(StudentTaskStatusRepository studentTaskStatusRepository) {
        this.studentTaskStatusRepository = studentTaskStatusRepository;
    }

    @Override
    public StudentTaskStatus findStudentTaskStatusByLabelAndTaskAndStudent(Label label, Task task, User user) {
        return studentTaskStatusRepository.findStudentTaskStatusByLabelAndTaskAndStudent(label, task, user).orElse(null);
    }

    @Override
    public StudentTaskStatus create(StudentTaskStatus entity) {
        return studentTaskStatusRepository.saveAndFlush(entity);
    }

    @Override
    public StudentTaskStatus save(StudentTaskStatus task) {
        return studentTaskStatusRepository.saveAndFlush(task);
    }

    @Override
    public void update(StudentTaskStatus entity) {
        studentTaskStatusRepository.saveAndFlush(entity);
    }
}
