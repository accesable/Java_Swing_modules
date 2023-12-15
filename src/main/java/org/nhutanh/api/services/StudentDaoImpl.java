package org.nhutanh.api.services;

import org.nhutanh.api.DatabaseConnection;
import org.nhutanh.api.dao.StudentDao;
import org.nhutanh.api.models.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

    @Override
    public Student getStudentById(Long id) {
        String sql = "SELECT * FROM student WHERE id = ?";
        Student student = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    student = mapRowToStudent(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Proper exception handling should be implemented
        }
        return student;
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM student";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                students.add(mapRowToStudent(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Proper exception handling should be implemented
        }
        return students;
    }

    @Override
    public void addStudent(Student student) {
        String sql = "INSERT INTO student (full_name, phone_number, age) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, student.getFullName());
            pstmt.setString(2, student.getPhoneNumber());
            pstmt.setInt(3, student.getAge());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Proper exception handling should be implemented
        }
    }

    @Override
    public void updateStudent(Student student) {
        String sql = "UPDATE student SET full_name = ?, phone_number = ?, age = ? WHERE id = ?";

        Student inDataStudent = this.getStudentById(student.getId());
        if (student.getFullName()!=null){
            inDataStudent.setFullName(student.getFullName());
        }
        if (student.getPhoneNumber()!=null){
            inDataStudent.setPhoneNumber(student.getPhoneNumber());
        }
        if (student.getAge()!=0){
            inDataStudent.setAge(student.getAge());
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, inDataStudent.getFullName());
            pstmt.setString(2, inDataStudent.getPhoneNumber());
            pstmt.setInt(3, inDataStudent.getAge());
            pstmt.setLong(4, inDataStudent.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Proper exception handling should be implemented
        }
    }

    @Override
    public void deleteStudent(Long id) {
        String sql = "DELETE FROM student WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Proper exception handling should be implemented
        }
    }

    // Other CRUD operations...

    private Student mapRowToStudent(ResultSet rs) throws SQLException {
        Student student = new Student();
        student.setId(rs.getLong("id"));
        student.setFullName(rs.getString("full_name"));
        student.setPhoneNumber(rs.getString("phone_number"));
        student.setAge(rs.getInt("age"));
        // Handling certificates is more complex and might require additional queries
        return student;
    }
}
