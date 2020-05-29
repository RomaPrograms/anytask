package com.bsu.famcs.anytask.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    private int id;

    private String name;

    private String dueDate;

    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToOne(mappedBy = "task", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private StudentTaskStatus studentTaskStatus;

    public Task() {
    }

    public Task(String name, String dueDate, String description, Course course, StudentTaskStatus studentTaskStatus) {
        this.name = name;
        this.dueDate = dueDate;
        this.description = description;
        this.course = course;
        this.studentTaskStatus = studentTaskStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public StudentTaskStatus getStudentTaskStatus() {
        return studentTaskStatus;
    }

    public void setStudentTaskStatus(StudentTaskStatus studentTaskStatus) {
        this.studentTaskStatus = studentTaskStatus;
    }
}
