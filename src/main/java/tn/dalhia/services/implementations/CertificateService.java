package tn.dalhia.services.implementations;

import com.google.zxing.WriterException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.dalhia.entities.*;
import tn.dalhia.entities.enumerations.CertificateType;
import tn.dalhia.repositories.CertificateRepository;
import tn.dalhia.repositories.CourseRepository;
import tn.dalhia.repositories.QuizRepository;
import tn.dalhia.services.ICertificateService;
import tn.dalhia.utils.QRCodeGenerator;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class CertificateService implements ICertificateService {

    @Autowired
    CertificateRepository certificateRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    QuizRepository quizRepository;

    @Override
    public List<Certificate> getAll(){
        return certificateRepository.findAll();
    }

    /*@Override
    public Certificate add(Certificate certificate , Long courseId, Quiz quizUser , Long idQuiz){
        Course cc = courseRepository.findById(courseId).orElse(null);
        Quiz q = quizRepository.findById(idQuiz).orElse(null);
        boolean resC = true, resU=true;
        int note=0;
        if(cc != null) {
            if(cc.getPrice()==0){
                certificate.setCertificateType(CertificateType.HOBBY);
            } else {
                for (Question question : q.getQuestions()){
                    for(Answer answer : question.getAnswers()){
                        resC = answer.getCorrect();

                        for (Question questionUser : quizUser.getQuestions()) {
                            for (Answer answerUser : questionUser.getAnswers()) {
                                resU = answerUser.getCorrect();
                            }
                        }
                    }
                }

                if (resC == resU){
                    note = note + 2;
                }
                if (note > 15){
                    certificate.setCertificateType(CertificateType.PROFESSIONAL);
                }else {
                    return null;
                }
            }
            certificate.setTitle(cc.getName());
            certificate.setDateAffection(LocalDateTime.now());
            certificate.setDateAdded(LocalDateTime.now());
            certificate.setCourse(cc);
            return certificateRepository.save(certificate);
        }
        return null;
    }*/
    @Override
    public Certificate add(Long id){
        Course c = courseRepository.findById(id).orElse(null);
        String QR_CODE_IMAGE_PATH = "./src/main/resources/static/img/QRCode.png";
        if (c==null)
            return null;

        String qrText= c.getName(); //el donnée elli bech ykoun fi tkhalbiza ta3 el QR
        try {
            // Generate and Save Qr Code Image in static/image folder
            QRCodeGenerator.generateQRCodeImage(qrText,250,250,QR_CODE_IMAGE_PATH);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
        Certificate certificate = new Certificate();
        certificate.setTitle(c.getName());
        certificate.setDateAffection(LocalDateTime.now());
        certificate.setDateAdded(LocalDateTime.now());
        certificate.setCourse(c);
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
            certificateRepository.delete(c);
            return true;
        }
        return false;
    }


}
