package org.nhutanh.api.services;
import org.nhutanh.api.DatabaseConnection;
import org.nhutanh.api.dao.CertificateDao;
import org.nhutanh.api.models.Certificate;
import org.nhutanh.api.models.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CertificateDaoImpl implements CertificateDao {

    @Override
    public Certificate getCertificateById(Long id) {
        String sql = "SELECT * FROM certificate WHERE id = ?";
        Certificate certificate = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    certificate = mapRowToCertificate(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Proper exception handling should be implemented
        }
        return certificate;
    }


    @Override
    public List<Certificate> getAllCertificates() {
        List<Certificate> certificates = new ArrayList<>();
        String sql = "SELECT * FROM certificate";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                certificates.add(mapRowToCertificate(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Proper exception handling should be implemented
        }
        return certificates;
    }

    @Override
    public void addCertificate(Certificate certificate) {
        String sql = "INSERT INTO certificate (title, issuing_organization, issue_date, description, student_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            mapRowToCertificate(certificate, pstmt);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Proper exception handling should be implemented
        }
    }

    @Override
    public void updateCertificate(Certificate certificate) {
        String sql = "UPDATE certificate SET title = ?, issuing_organization = ?, issue_date = ?, description = ?, student_id = ? WHERE id = ?";

        Certificate inDataCert = this.getCertificateById(certificate.getId());
        if (certificate.getTitle()!=null){
            inDataCert.setTitle(certificate.getTitle());
        }
        if (certificate.getDescription()!=null){
            inDataCert.setDescription(certificate.getDescription());
        }
        if (certificate.getIssueDate()!=null){
            inDataCert.setIssueDate(certificate.getIssueDate());
        }
        if (certificate.getIssuingOrganization()!=null){
            inDataCert.setIssuingOrganization(certificate.getIssuingOrganization());
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            mapRowToCertificate(inDataCert, pstmt);
            pstmt.setLong(6, certificate.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Proper exception handling should be implemented
        }
    }

    private void mapRowToCertificate(Certificate certificate, PreparedStatement pstmt) throws SQLException {
        pstmt.setString(1, certificate.getTitle());
        pstmt.setString(2, certificate.getIssuingOrganization());
        pstmt.setDate(3, new Date(certificate.getIssueDate().getTime()));
        pstmt.setString(4, certificate.getDescription());
        pstmt.setLong(5, certificate.getStudent().getId());
    }

    @Override
    public void deleteCertificate(Long id) {
        String sql = "DELETE FROM certificate WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Proper exception handling should be implemented
        }
    }

    private Certificate mapRowToCertificate(ResultSet rs) throws SQLException {
        Certificate certificate = new Certificate();
        certificate.setId(rs.getLong("id"));
        certificate.setTitle(rs.getString("title"));
        certificate.setIssuingOrganization(rs.getString("issuing_organization"));
        certificate.setIssueDate(rs.getDate("issue_date"));
        certificate.setDescription(rs.getString("description"));
        // Assuming 'student_id' is the FK column in 'certificate' table
        Long studentId = rs.getLong("student_id");
        if (!rs.wasNull()) {
            Student student = new Student(); // Simplified; in real scenario, you might fetch the complete Student object
            student.setId(studentId);
            certificate.setStudent(student);
        }
        return certificate;
    }

    // Helper method to map ResultSet row to Certificate object...
}

