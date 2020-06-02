package com.bsu.famcs.anytask.service.interfaces;

import com.bsu.famcs.anytask.entity.Course;

import java.util.List;

public interface CourseService {

    Course findByName(String courseName);

    List<Course> findAll();

    Course create(Course entity);

    Course save(Course course);

    void delete(Course course);
}
