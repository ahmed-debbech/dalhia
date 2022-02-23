package tn.dalhia.services;

import tn.dalhia.entities.Certificate;
import tn.dalhia.entities.Question;

import java.util.List;

public interface IQuestionService {

    List<Question> getAll();
    Question add(Question question);
    Question modify(Question question, Long id);
    Question get(Long id);
    boolean delete(Long id);
}
