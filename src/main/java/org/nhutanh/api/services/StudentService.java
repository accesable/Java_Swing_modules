package org.nhutanh.api.services;

import org.nhutanh.api.dao.CertificateDao;
import org.nhutanh.api.dao.StudentDao;
import org.nhutanh.api.models.Certificate;
import org.nhutanh.api.models.Student;

import java.util.List;
import java.util.stream.Collectors;

public class StudentService {

    private StudentDao studentDao;
    private CertificateDao certificateDao;

    public StudentService() {
        this.studentDao = new StudentDaoImpl(); // Assuming default implementations
        this.certificateDao = new CertificateDaoImpl();
    }

    // Method to get a student by ID
    public Student getStudentById(Long id) {
        return studentDao.getStudentById(id);
    }

    // Method to get all students
    public List<Student> getAllStudents() {
        return studentDao.getAllStudents();
    }

    // Method to add a new student
    public void addStudent(Student student) {
        studentDao.addStudent(student);
    }

    // Method to update a student's details
    public void updateStudent(Student student) {
        studentDao.updateStudent(student);
    }

    // Method to delete a student
    public void deleteStudent(Long id) {
        studentDao.deleteStudent(id);
    }

    // Method to add a certificate to a student
    public void addCertificateToStudent(Certificate certificate, Long studentId) {
        Student student = studentDao.getStudentById(studentId);
        if (student != null) {
            certificate.setStudent(student);
            certificateDao.addCertificate(certificate);
        } else {
            throw new RuntimeException("Student not found with ID: " + studentId);
        }
    }

    public void addCertificatesToStudent(List<Certificate> certificates , Long studentId){
        certificates.forEach(certificate -> {
            this.addCertificateToStudent(certificate,studentId);
        });
    }

    // Method to get all certificates of a student
    public List<Certificate> getCertificatesOfStudent(Long studentId) {
        List<Certificate> certificates = certificateDao.getAllCertificates();
        return certificates.stream()
                .filter(certificate -> certificate.getStudent() != null && certificate.getStudent().getId().equals(studentId))
                .collect(Collectors.toList());
    }

    // Additional methods as required...
}

