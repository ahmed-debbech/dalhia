package tn.dalhia.services.implementations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.dalhia.entities.Phase;
import tn.dalhia.entities.Question;
import tn.dalhia.entities.Quiz;
import tn.dalhia.repositories.QuestionRepository;
import tn.dalhia.repositories.QuizRepository;
import tn.dalhia.services.IQuestionService;

import java.util.List;

@Service
@Slf4j
public class QuestionService implements IQuestionService {


    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    QuizRepository quizRepository;

    @Override
    public List<Question> getAllByQuiz(Long id) {
        Quiz q =quizRepository.findById(id).orElse(null);
        if(q == null)
            return null;

            return q.getQuestions();
    }

    @Override
    public Question add(Question question, Long id){
        Quiz q = quizRepository.findById(id).orElse(null);
        if (q == null)
            return null;

        q.getQuestions().add(question);
        quizRepository.save(q);
        return question;

    }

    @Override
    public Question modify(Question question , Long id){
        Question q1 = this.get(id);
        if(q1 == null){
            return null;
        }

        return questionRepository.save(question);
    }

    @Override
    public Question get(Long id){
        return questionRepository.findById(id).orElse(null);
    }

    @Override
    public  boolean delete(Long id){
        Question q = questionRepository.findById(id).orElse(null);
        if(q != null){
            questionRepository.delete(q);
            return true;
        }
        return false;
    }
}
