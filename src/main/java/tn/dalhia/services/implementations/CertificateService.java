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
    public List<Certificate> getAll(){
        return certificateRepository.findAll();
    }

    @Override
    public Certificate add(Certificate certificate){

        return certificateRepository.save(certificate);
    }

    @Override
    public Certificate modify(Certificate certificate , Long id){
        Certificate c1 = this.get(id);
        if(c1 == null){
            return null;
        }
        c1.setTitle(certificate.getTitle());
        c1.setCourse(certificate.getCourse());
        c1.setCertificateType(certificate.getCertificateType());
        return certificateRepository.save(certificate);
    }

    @Override
    public Certificate get(Long id){
        return certificateRepository.findById(id).orElse(null);
    }

    @Override
    public  boolean delete(Long id){
        Certificate c = certificateRepository.findById(id).orElse(null);
        if(c != null){
            certificateRepository.save(c);
            return true;
        }
        return false;
    }

}
