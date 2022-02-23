package tn.dalhia.services.implementations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.dalhia.entities.Certificate;
import tn.dalhia.repositories.CertificateRepository;
import tn.dalhia.services.ICertificateService;

import java.util.List;

@Service
@Slf4j
public class CertificateService implements ICertificateService {

    @Autowired
    CertificateRepository certificateRepository;

    @Override
    public List<Certificate> retrieveAllCertificates(){
        List<Certificate> listCertificate= certificateRepository.findAll();
        for(Certificate c:listCertificate)
        {
            log.info("Certificate:" + c.getTitle() );
        }
        return listCertificate;
    }

    @Override
    public Certificate addCertificate(Certificate c) {
        certificateRepository.save(c);
        return c;
    }

    @Override
    public void deleteCertificate(Long id) {
        certificateRepository.deleteById(id);
    }

    @Override
    public Certificate updateCertificate(Certificate c) {
        certificateRepository.save(c);
        return c;
    }

    @Override
    public Certificate retrieveCertificate(Long id) {
        Certificate c= certificateRepository.findById(id).get();
        return c;
    }
}
