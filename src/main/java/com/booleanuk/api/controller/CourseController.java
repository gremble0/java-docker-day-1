package com.booleanuk.api.controller;

import com.booleanuk.api.model.Course;
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
@RequestMapping("courses")
public class CourseController {
  @Autowired
  private CourseRepository repository;

  @GetMapping
  public ResponseEntity<List<Course>> get() {
    return ResponseEntity.ok(repository.findAll());
  }

  @GetMapping("{id}")
  public ResponseEntity<Course> get(@PathVariable int id) {
    return ResponseEntity.ok(this.repository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
  }

  @PostMapping
  public ResponseEntity<Course> post(@RequestBody Course course) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(this.repository.save(course));
  }

  @PutMapping("{id}")
  public ResponseEntity<Course> put(@PathVariable int id, @RequestBody Course course) {
    return this.repository.findById(id).map(existing -> {
      existing.update(course);
      return ResponseEntity.status(HttpStatus.CREATED).body(this.repository.save(existing));
    }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Course> delete(@PathVariable int id) {
    var existing = this.repository.findById(id);
    if (existing.isEmpty())
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    this.repository.deleteById(id);
    return ResponseEntity.ok(existing.get());
  }
}