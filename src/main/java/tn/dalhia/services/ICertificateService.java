package tn.dalhia.services;

import tn.dalhia.entities.Certificate;
import tn.dalhia.entities.Quiz;

import java.util.List;

public interface ICertificateService {

    List<Certificate> getAll();
    Certificate add(Certificate certificate, Long courseId , Quiz quizUser , Long idQuiz);
    Certificate modify(Certificate certificate, Long id);
    Certificate get(Long id);
    boolean delete(Long id);
}
