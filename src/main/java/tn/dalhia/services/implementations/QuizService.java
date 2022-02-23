package tn.dalhia.services.implementations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.dalhia.entities.Quiz;
import tn.dalhia.repositories.QuizRepository;
import tn.dalhia.services.IQuizService;

import java.util.List;

@Service
@Slf4j
public class QuizService implements IQuizService {


    @Autowired
    QuizRepository quizRepository;

    @Override
    public List<Quiz> getAll(){
        return quizRepository.findAll();
    }

    @Override
    public Quiz add(Quiz quiz){

        return quizRepository.save(quiz);
    }

    @Override
    public Quiz modify(Quiz quiz , Long id){
        Quiz q1 = this.get(id);
        if(q1 == null){
            return null;
        }

        return quizRepository.save(quiz);
    }

    @Override
    public Quiz get(Long id){
        return quizRepository.findById(id).orElse(null);
    }

    @Override
    public  boolean delete(Long id){
        Quiz q = quizRepository.findById(id).orElse(null);
        if(q != null){
            quizRepository.save(q);
            return true;
        }
        return false;
    }
}
