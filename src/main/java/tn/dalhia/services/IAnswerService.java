package tn.dalhia.services;

import tn.dalhia.entities.Answer;

import java.util.List;

public interface IAnswerService {

    List<Answer> getAll();
    List<Answer> getAllByQuestion(Long id);
    Answer add(Answer answer, Long id);
    Answer modify(Answer answer, Long id);
    Answer get(Long id);
    boolean delete(Long id);
}
