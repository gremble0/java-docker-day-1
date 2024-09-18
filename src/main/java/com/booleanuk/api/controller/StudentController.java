package com.booleanuk.api.controller;

import com.booleanuk.api.model.Student;
import com.booleanuk.api.repository.CourseRepository;
import com.booleanuk.api.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {
  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private CourseRepository courseRepository;

  @GetMapping
  public ResponseEntity<List<Student>> get() {
    return ResponseEntity.ok(studentRepository.findAll());
  }

  @GetMapping("{id}")
  public ResponseEntity<Student> get(@PathVariable int id) {
    return ResponseEntity.ok(this.studentRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
  }

  @PostMapping
  public ResponseEntity<Student> post(@RequestBody Student student) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(this.studentRepository.save(student));
  }

  @PutMapping("{id}")
  public ResponseEntity<Student> put(@PathVariable int id, @RequestBody Student student) {
    return this.studentRepository.findById(id).map(existing -> {
      existing.update(student);
      return ResponseEntity.status(HttpStatus.CREATED).body(this.studentRepository.save(existing));
    }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Student> delete(@PathVariable int id) {
    var existing = this.studentRepository.findById(id);
    if (existing.isEmpty())
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    this.studentRepository.deleteById(id);
    return ResponseEntity.ok(existing.get());
  }
}