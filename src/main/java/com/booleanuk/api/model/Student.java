package com.booleanuk.api.model;

import jakarta.persistence.*;

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
  private String courseTitle;

  @Column(nullable = false)
  private String birthDay;

  @Column(nullable = false)
  private String courseStartDate;

  @Column(nullable = false)
  private double averageGrade;
}