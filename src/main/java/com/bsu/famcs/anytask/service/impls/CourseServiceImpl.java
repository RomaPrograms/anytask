package com.bsu.famcs.anytask.service.impls;

import com.bsu.famcs.anytask.entity.Course;
import com.bsu.famcs.anytask.repository.CourseRepository;
import com.bsu.famcs.anytask.service.interfaces.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course findByName(String courseName) {
        return courseRepository.findByName(courseName).orElse(null);
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course create(Course entity) {
        return courseRepository.saveAndFlush(entity);
    }

    @Override
    public Course save(Course course) {
        return courseRepository.saveAndFlush(course);
    }

    @Override
    public void delete(Course course) {
       courseRepository.delete(course);
    }
}
