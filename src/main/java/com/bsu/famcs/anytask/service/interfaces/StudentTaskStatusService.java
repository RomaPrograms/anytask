package com.bsu.famcs.anytask.service.interfaces;

import com.bsu.famcs.anytask.entity.Label;
import com.bsu.famcs.anytask.entity.StudentTaskStatus;
import com.bsu.famcs.anytask.entity.Task;
import com.bsu.famcs.anytask.entity.User;

public interface StudentTaskStatusService  {

    StudentTaskStatus findStudentTaskStatusByLabelAndTaskAndStudent(Label label, Task task, User user);

    StudentTaskStatus create(StudentTaskStatus entity);

    StudentTaskStatus save(StudentTaskStatus task);

    void update(StudentTaskStatus entity);
}
