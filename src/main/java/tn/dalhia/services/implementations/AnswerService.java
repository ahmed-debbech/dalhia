package tn.dalhia.services.implementations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.dalhia.entities.Answer;
import tn.dalhia.entities.Question;
import tn.dalhia.repositories.AnswerRepository;
import tn.dalhia.repositories.QuestionRepository;
import tn.dalhia.services.IAnswerService;

import java.util.List;

@Service
@Slf4j
public class AnswerService implements IAnswerService {
    @Autowired
    AnswerRepository answerRepository;
    @Autowired
    QuestionRepository questionRepository;

    @Override
    public List<Answer> getAll(){
        return answerRepository.findAll();
    }


    /*@Override
    public List<Answer> getAllByQuestion( Long id){
        Question q = questionRepository.findById(id).orElse(null);
        if (q == null)
            return null;

        return q.getAnswers();
    }*/
    @Override
    public Answer add(Answer answer){
        return answerRepository.save(answer);
    }

    @Override
    public Answer modify(Answer answer , Long id){
        Answer a1 = this.get(id);
        if(a1 == null){
            return null;
        }
        return answerRepository.save(answer);
    }

    @Override
    public Answer get(Long id){
        return answerRepository.findById(id).orElse(null);
    }

    @Override
    public  boolean delete(Long id){
        Answer a = answerRepository.findById(id).orElse(null);
        if(a != null){
            answerRepository.delete(a);
            return true;
        }
        return false;
    }
}
