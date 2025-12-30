package com.example.sms.service.impl;

import com.example.sms.dto.StudentDto;
import com.example.sms.entity.Student;
import com.example.sms.exception.ResourceNotFoundException;
import com.example.sms.mapper.StudentMapper;
import com.example.sms.repository.StudentRepository;
import com.example.sms.service.StudentService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

  private StudentRepository studentRepository;

  @Override
  public StudentDto createStudent(StudentDto studentDto) {
    Student student = StudentMapper.mapToStudent(studentDto);
    Student savedStudent = studentRepository.save(student);
    return StudentMapper.mapToStudentDto(savedStudent);
  }

  @Override
  public StudentDto getStudentById(Long studentId) {
    Student student = studentRepository.findById(studentId)
        .orElseThrow(() ->
            new ResourceNotFoundException("Student doesn't exist with id: " + studentId));
    return StudentMapper.mapToStudentDto(student);
  }

  @Override
  public List<StudentDto> getAllStudents() {
    List<Student> students = studentRepository.findAll();
    return students.stream().map((student) -> StudentMapper.mapToStudentDto(student))
        .collect(Collectors.toList());
  }

  @Override
  public StudentDto updateStudent(Long studentId, StudentDto updatedStudent) {
    Student student = studentRepository.findById(studentId).orElseThrow(
        () -> new ResourceNotFoundException("Student doesn't exist with id: " + studentId)
    );

    student.setFirstName(updatedStudent.getFirstName());
    student.setLastName(updatedStudent.getLastName());
    student.setEmail(updatedStudent.getEmail());

    Student updatedStudentObj = studentRepository.save(student);

    return StudentMapper.mapToStudentDto(updatedStudentObj);
  }

  @Override
  public void deleteStudent(Long studentId) {
    Student student = studentRepository.findById(studentId).orElseThrow(
        () -> new ResourceNotFoundException("Student doesn't exist with id: " + studentId)
    );

    studentRepository.deleteById(studentId);
  }

}
