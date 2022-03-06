package tn.dalhia.services.implementations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.dalhia.entities.Course;
import tn.dalhia.entities.Phase;
import tn.dalhia.repositories.CourseRepository;
import tn.dalhia.repositories.PhaseRepository;
import tn.dalhia.services.IPhaseService;

import java.util.List;
@Service
@Slf4j
public class PhaseService implements IPhaseService {

    @Autowired
    PhaseRepository phaseRepository;
    @Autowired
    CourseRepository courseRepository;

    @Override
    public List<Phase> getAll(){
        return phaseRepository.findAll();
    }

    @Override
    public Phase add(Phase phase, Long id){
        Course c = courseRepository.findById(id).orElse(null);
        if (c == null)
            return null;

        phase.setNumber(c.getNbrPhases()+1);
        c.getPhases().add(phase);
        c.setNbrPhases(c.getNbrPhases()+1);
        courseRepository.save(c);
        return phase;
    }

    @Override
    public Phase modify(Phase phase , Long id){
        Phase ph1 = this.get(id);
        if(ph1 == null){
            return null;
        }
        return phaseRepository.save(phase);
    }

    @Override
    public Phase get(Long id){
        return phaseRepository.findById(id).orElse(null);
    }

    @Override
    public List<Phase> getAllByCourse(Long id){
        Course c = courseRepository.findById(id).orElse(null);
        if (c == null)
            return null;

        return c.getPhases();
    }

    @Override
    public  Boolean delete(Long id, Long idCourse){
        Phase ph = phaseRepository.findById(id).orElse(null);
        Course c = courseRepository.findById(idCourse).orElse(null);

        if(ph != null && c!=null){
            c.setNbrPhases(c.getNbrPhases()-1);
            courseRepository.save(c);
            phaseRepository.delete(ph);
            return true;
        }
        return false;
    }
}
