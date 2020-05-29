package com.bsu.famcs.anytask.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    private int id;

    private String name;

    private String surname;

    @Column(name = "login")
    private String username;

    private boolean active;

    private String password;

    @Transient
    private String passwordConfirm;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student", orphanRemoval = true)
    private Set<StudentTaskStatus> studentTaskStatusSet = new HashSet<>();

    @ManyToMany(mappedBy = "studentSet", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Course> studentCourseSet = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teacher", orphanRemoval = true)
    private Set<Course> teacherCourseSet = new HashSet<>();

    public User() {
    }

    public User(String name, String surname, String username, boolean active, String password, String passwordConfirm, Set<Role> roles,
                Set<StudentTaskStatus> studentTaskStatusSet, Set<Course> studentCourseSet, Set<Course> teacherCourseSet) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.active = active;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.roles = roles;
        this.studentTaskStatusSet = studentTaskStatusSet;
        this.studentCourseSet = studentCourseSet;
        this.teacherCourseSet = teacherCourseSet;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<StudentTaskStatus> getStudentTaskStatusSet() {
        return studentTaskStatusSet;
    }

    public void setStudentTaskStatusSet(Set<StudentTaskStatus> studentTaskStatusSet) {
        this.studentTaskStatusSet = studentTaskStatusSet;
    }

    public Set<Course> getStudentCourseSet() {
        return studentCourseSet;
    }

    public void setStudentCourseSet(Set<Course> studentCourseSet) {
        this.studentCourseSet = studentCourseSet;
    }

    public Set<Course> getTeacherCourseSet() {
        return teacherCourseSet;
    }

    public void setTeacherCourseSet(Set<Course> teacherCourseSet) {
        this.teacherCourseSet = teacherCourseSet;
    }
}
