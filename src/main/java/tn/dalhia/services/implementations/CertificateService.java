package tn.dalhia.services.implementations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.dalhia.entities.Certificate;
import tn.dalhia.entities.Course;
import tn.dalhia.entities.Phase;
import tn.dalhia.entities.enumerations.CertificateType;
import tn.dalhia.repositories.CertificateRepository;
import tn.dalhia.repositories.CourseRepository;
import tn.dalhia.services.ICertificateService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class CertificateService implements ICertificateService {

    @Autowired
    CertificateRepository certificateRepository;
    @Autowired
    CourseRepository courseRepository;

    @Override
    public List<Certificate> getAll(){
        return certificateRepository.findAll();
    }

    @Override
    public Certificate add(Certificate certificate , Long courseId){
        Course cc = courseRepository.findById(courseId).orElse(null);
        if(cc != null) {
            if(cc.getPrice()==0)
                certificate.setCertificateType(CertificateType.HOBBY);
            else
                certificate.setCertificateType(CertificateType.PROFESSIONAL);
            certificate.setTitle(cc.getName());
            certificate.setDateAffection(LocalDateTime.now());
            certificate.setDateAdded(LocalDateTime.now());
            certificate.setCourse(cc);
            return certificateRepository.save(certificate);
        }
        return null;
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
            certificateRepository.delete(c);
            return true;
        }
        return false;
    }

}
