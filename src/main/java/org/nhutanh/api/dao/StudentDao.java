package org.nhutanh.api.dao;

import org.nhutanh.api.models.Student;

import java.util.List;

public interface StudentDao {
    Student getStudentById(Long id);
    List<Student> getAllStudents();
    void addStudent(Student student);
    void updateStudent(Student student);
    void deleteStudent(Long id);
}
