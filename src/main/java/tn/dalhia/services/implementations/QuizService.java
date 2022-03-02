package tn.dalhia.services.implementations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.dalhia.entities.Course;
import tn.dalhia.entities.Phase;
import tn.dalhia.entities.Quiz;
import tn.dalhia.repositories.CourseRepository;
import tn.dalhia.repositories.PhaseRepository;
import tn.dalhia.repositories.QuizRepository;
import tn.dalhia.services.IQuizService;

import java.util.List;

@Service
@Slf4j
public class QuizService implements IQuizService {


    @Autowired
    QuizRepository quizRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    PhaseRepository phaseRepository;

    @Override
    public List<Quiz> getAll(){
        return quizRepository.findAll();
    }

    @Override
    public Quiz add(Quiz quiz,Long id,Phase phase){
        Course c = courseRepository.findById(id).orElse(null);
        if(c == null)
            return null;
        for (Phase p : c.getPhases())
        {
            if (c.getPhases()==null)
                return null;
            else
            if (p.getFinalPhase()== false){
                phase.setFinalPhase(true);
                phase.setTitle("QUIZ");
                phase.setNumber(p.getNumber()+1);
                phase.setDuration(30);
                c.getPhases().add(phase);
                phase.getQuiz().add(quiz);
                courseRepository.save(c);
                phaseRepository.save(phase);
            }
            return quiz;
        }
        return null;
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
            quizRepository.delete(q);
            return true;
        }
        return false;
    }
}
