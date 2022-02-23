package tn.dalhia.services;

import tn.dalhia.entities.Certificate;

import java.util.List;

public interface ICertificateService {

    List<Certificate> retrieveAllCertificates();

    Certificate addCertificate(Certificate c);

    void deleteCertificate(Long id);

    Certificate updateCertificate(Certificate c);

    Certificate retrieveCertificate(Long id);
}
