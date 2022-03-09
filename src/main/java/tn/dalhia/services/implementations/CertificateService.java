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
import tn.dalhia.repositories.UserRepository;
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
    @Autowired
    UserRepository userRepository;
    @Override
    public List<Certificate> getAll(){
        return certificateRepository.findAll();
    }

    @Override
    public Certificate add(Long courseId, Quiz quizUser , Long idQuiz){
        Course cc = courseRepository.findById(courseId).orElse(null);
        Quiz quizC = quizRepository.findById(idQuiz).orElse(null); //correction
        Certificate certificate = new Certificate();

        if(cc.getPrice()==0) {
            certificate.setCertificateType(CertificateType.HOBBY);
        }else if(this.noteQuiz(courseId, quizUser, idQuiz) >= 30){
            System.err.println("note");
            certificate.setCertificateType(CertificateType.PROFESSIONAL);
        }

        certificate.setTitle(cc.getName());
        certificate.setDateAffection(LocalDateTime.now());
        certificate.setDateAdded(LocalDateTime.now());
        certificate.setCourse(cc);
        //certificate.getUser().setId(1L);
        this.generateQR(certificate);

        return certificateRepository.save(certificate);
    }


    private int noteQuiz (Long courseId, Quiz quizUser , Long idQuiz) {
        System.err.println(quizUser);
        Course cc = courseRepository.findById(courseId).orElse(null);
        Quiz quizC = quizRepository.findById(idQuiz).orElse(null); //correction
        boolean resC = true, resU = true;
        int note = 0;
        int i = 0, j = 0;
        Certificate certificate = new Certificate();
        if (cc != null) {
            if (cc.getPrice() == 0) {
                certificate.setCertificateType(CertificateType.HOBBY);
            } else {
                for (Question question : quizC.getQuestions()) {
                    for (Answer answer : question.getAnswers()) {
                        resC = answer.getCorrect(); //response correct d'une question
                        for (Question questionUser : quizUser.getQuestions()) {
                            if (question.getText().equals(questionUser.getText())) {
                                for (Answer answerUser : questionUser.getAnswers()) {
                                    if (answer.getProposition().equals(answerUser.getProposition())) {
                                        resU = answerUser.getCorrect();
                                        if (resU == resC) {
                                            note += 1;
                                        }
                                        break;
                                    }
                                }
                                break;

                            }
                        }
                    }
                }

                System.err.println(note);
                User user = userRepository.findById(1L).orElse(null);
                for (CourseProgress courseProgress : user.getCourseProgresses()) {
                    int att = courseProgress.getAttempts();
                    if (att == 3){
                        System.err.println("NO");
                        break;
                    }
                    if (cc.getId() == courseProgress.getCourse().getId()) {
                        courseProgress.setNoteQuiz(note);
                        int diff = courseProgress.getAttDate().plusHours(48).compareTo(LocalDateTime.now());
                        if ((att == 2) && (diff > 0)){
                            System.err.println("you have to wait 48 hours !!");
                            break;
                        }else{
                            att += 1;
                            courseProgress.setAttempts(att);
                            courseProgress.setAttDate(LocalDateTime.now());
                        }
                    }
                }
            }
        }
        return note;
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
