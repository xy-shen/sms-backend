package com.example.sms.service;

import com.example.sms.dto.StudentDto;
import com.example.sms.entity.Student;
import java.util.List;

public interface StudentService {
  StudentDto createStudent(StudentDto studentDto);

  StudentDto getStudentById(Long studentId);

  List<StudentDto> getAllStudents();

  StudentDto updateStudent(Long studentId, StudentDto updatedStudent);

  void deleteStudent(Long studentId);
}
