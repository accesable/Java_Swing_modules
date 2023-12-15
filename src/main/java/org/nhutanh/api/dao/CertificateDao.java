package org.nhutanh.api.dao;

import org.nhutanh.api.models.Certificate;

import java.util.List;

public interface CertificateDao {
    Certificate getCertificateById(Long id);
    List<Certificate> getAllCertificates();
    void addCertificate(Certificate certificate);
    void updateCertificate(Certificate certificate);
    void deleteCertificate(Long id);
}

