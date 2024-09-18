package com.booleanuk.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "students")
public class Student {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(nullable = false)
  private String firstName;

  @Column(nullable = false)
  private String lastName;

  @Column(nullable = false)
  private String birthDay;

  @Column(nullable = false)
  private String courseStartDate;

  @Column(nullable = false)
  private double averageGrade;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
      name = "student_courses",
      joinColumns = @JoinColumn(name = "student_id"),
      inverseJoinColumns = @JoinColumn(name = "course_id")
  )
  //@JsonIgnoreProperties("students")
  private Set<Course> courses;

  public void update(Student other) {
    this.firstName = other.firstName;
    this.lastName = other.lastName;
    this.courseTitle = other.courseTitle;
    this.birthDay = other.birthDay;
    this.courseStartDate = other.courseStartDate;
    this.averageGrade = other.averageGrade;
  }
}