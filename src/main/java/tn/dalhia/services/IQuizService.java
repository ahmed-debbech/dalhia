package tn.dalhia.services;

import tn.dalhia.entities.Quiz;

import java.util.List;

public interface IQuizService {

    List<Quiz> getAll();
    Quiz add(Quiz quiz);
    Quiz modify(Quiz quiz, Long id);
    Quiz get(Long id);
    boolean delete(Long id);
}
