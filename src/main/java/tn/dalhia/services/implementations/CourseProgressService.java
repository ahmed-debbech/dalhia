package tn.dalhia.services.implementations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import tn.dalhia.entities.*;
import tn.dalhia.entities.enumerations.CourseProgressStatus;
import tn.dalhia.repositories.CertificateRepository;
import tn.dalhia.repositories.CourseProgressRepository;
import tn.dalhia.repositories.CourseRepository;
import tn.dalhia.repositories.UserRepository;
import tn.dalhia.services.ICourseProgressService;

import javax.jws.soap.SOAPBinding;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@Slf4j
public class CourseProgressService implements ICourseProgressService {

    @Autowired
    CourseProgressRepository courseProgressRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    CertificateRepository certificateRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public List<CourseProgress> getAll(){
        return courseProgressRepository.findAll();
    }

    @Override
    public CourseProgress add(CourseProgress courseProgress, Long id , Long idUser){
        Course c = courseRepository.findById(id).orElse(null);
        User user = userRepository.findById(idUser).orElse(null);
        if (c == null)
            return null;
        courseProgress.setCourseProgressStatus(CourseProgressStatus.IP);
        courseProgress.setDuration(0);
        courseProgress.setNoteQuiz(0);
        courseProgress.setEnrollDate(LocalDateTime.now());
        courseProgress.setCourse(c);
        courseProgress.setAttempts(0);
        user.getCourseProgresses().add(courseProgress);
         userRepository.save(user);
         return  courseProgress;
    }

    @Override
    public CourseProgress get(Long id){
        return courseProgressRepository.findById(id).orElse(null);
    }

    @Override
    public  boolean delete(Long id){
        CourseProgress c = courseProgressRepository.findById(id).orElse(null);
        if(c != null){
            courseProgressRepository.delete(c);
            return true;
        }
        return false;
    }

    @Override
    public CourseProgress modify (Long idC,Long id,int endPhase){
        Course c = courseRepository.findById(idC).orElse(null);
        CourseProgress courseProgress = courseProgressRepository.findById(id).orElse(null);
        if(courseProgress == null || c == null)
            return  null;
        int duration = 0;
        int totalDuration = 0; // el durée totale ta3 les phases lkoll illa el phase finale illi feha el quiz
        int totalDurationWC = 0; // el durée totale ta3 les phases lkoll bil phase finale
        int n=0; //houwa el nombre des phases illi
        int note=0;// note el quiz illi fil finalPhase illi fi this course
        for (Phase ph : c.getPhases()){
            if (ph.getFinalPhase()==true){
                    note = courseProgress.getNoteQuiz();
            }else{
                totalDuration = totalDuration + ph.getDuration();
                System.err.println(totalDuration);
            }
            totalDurationWC = totalDurationWC + ph.getDuration();
            System.err.println(totalDurationWC);
        }
        //int duration = courseProgress.getDuration();
        /*for (Phase p : c.getPhases()){
            if(p.getNumber() == endPhase){
                duration = duration + p.getDuration();
            }
        }*/
        for (Phase p : c.getPhases()){
            duration = duration + p.getDuration();
            n++;
            p.setStatus(1);
            if(n == endPhase){
                break;
            }
        }
        courseProgress.setDuration(duration);
        if (duration == totalDuration){
            courseProgress.setCourseProgressStatus(CourseProgressStatus.F);
        }else if ((duration == totalDurationWC) && (note >= 30)){
            courseProgress.setCourseProgressStatus(CourseProgressStatus.FWC);
        }else {
            courseProgress.setCourseProgressStatus(CourseProgressStatus.IP);
        }
        courseProgressRepository.save(courseProgress);
        return courseProgress;
    }

}
