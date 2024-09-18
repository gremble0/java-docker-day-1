package com.booleanuk.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "courses")
public class Course {
  @Id
  private String code;

  @ManyToMany(mappedBy = "courses", cascade = CascadeType.ALL)
  // @JsonIgnoreProperties("courses")
  private Set<Student> students;

  public void update(Course other) {
    this.students = other.students;
  }
}