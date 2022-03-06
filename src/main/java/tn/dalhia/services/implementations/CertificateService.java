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

    @Override
    public Certificate add(Long courseId, Quiz quizUser , Long idQuiz){
        Course cc = courseRepository.findById(courseId).orElse(null);
        Quiz quizC = quizRepository.findById(idQuiz).orElse(null); //correction
        //Quiz quizU = quizRepository.findById(idQuizUser).orElse(null); //reponse user
        boolean resC = true, resU=true;
        int note=0;
        int i=0,j=0;
        Certificate certificate = new Certificate();
        if(cc != null) {
            if(cc.getPrice()==0){
                certificate.setCertificateType(CertificateType.HOBBY);
            } else {
                for (Question question : quizC.getQuestions()){
                    System.err.println("ons1");
                    for(Answer answer : question.getAnswers()){
                        resC = answer.getCorrect(); //response correct d'une question
                        System.err.println("ons2");
                        for (Question questionUser : quizUser.getQuestions()) {
                            System.err.println("ons3");
                            if (question.getText().equals(questionUser.getText())){ //mouch 9a3ed yodkhol li houni
                                System.err.println("ons4");
                                for (Answer answerUser : questionUser.getAnswers()) {
                                    System.err.println("ons5");
                                    if (answer.getProposition().equals(answerUser.getProposition())){
                                        resU = answerUser.getCorrect();
                                        if(resU == resC){
                                            note += 1;
                                            System.err.println(note);
                                        }
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                    }
                }

                if (note >= 2){
                    certificate.setCertificateType(CertificateType.PROFESSIONAL);
                }else {
                    return null; // manja7ch
                }
            }
            certificate.setTitle(cc.getName());
            certificate.setDateAffection(LocalDateTime.now());
            certificate.setDateAdded(LocalDateTime.now());
            certificate.setCourse(cc);
            this.generateQR(certificate);
            return certificateRepository.save(certificate);
        }
        return null;
    }
    private void generateQR(Certificate certificate){
        String QR_CODE_IMAGE_PATH = "./src/main/resources/static/img/QRCode.png";

        String qrText= certificate.getTitle(); //el donnée elli bech ykoun fi tkhalbiza ta3 el QR
        try {
            // Generate and Save Qr Code Image in static/image folder
            QRCodeGenerator.generateQRCodeImage(qrText,250,250,QR_CODE_IMAGE_PATH);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
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
