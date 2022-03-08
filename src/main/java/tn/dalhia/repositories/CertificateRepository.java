package tn.dalhia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.dalhia.entities.Certificate;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Long> {

}
