package com.bsu.famcs.anytask.repository;

import com.bsu.famcs.anytask.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    Optional<Course> findByName(String courseName);
}
