package com.example.sms.controller;

import com.example.sms.dto.StudentDto;
import com.example.sms.service.StudentService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/students")
public class StudentController {

  private StudentService studentService;

  // Add student
  @PostMapping
  public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto) {
    StudentDto savedStudent = studentService.createStudent(studentDto);
    return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
  }

  // Get student
  @GetMapping("{id}")
  public ResponseEntity<StudentDto> getStudentById(@PathVariable("id") Long studentId) {
    StudentDto studentDto = studentService.getStudentById(studentId);
    return ResponseEntity.ok(studentDto);
  }

  // Get All Students
  @GetMapping
  public ResponseEntity<List<StudentDto>> getAllStudents() {
    List<StudentDto> students = studentService.getAllStudents();
    return ResponseEntity.ok(students);
  }

  // Update Student
  @PostMapping("{id}")
  public ResponseEntity<StudentDto> updateStudent(@PathVariable("id") Long studentId,
                                                  @RequestBody StudentDto updatedStudent) {
    StudentDto studentDto = studentService.updateStudent(studentId, updatedStudent);
    return ResponseEntity.ok(studentDto);
  }

  // Delete Student
  @DeleteMapping("{id}")
  public ResponseEntity<String> deleteStudent(@PathVariable("id") Long studentId) {
    studentService.deleteStudent(studentId);
    return ResponseEntity.ok("Student deleted successfully.");
  }
}
