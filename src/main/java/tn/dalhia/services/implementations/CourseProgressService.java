package tn.dalhia.services.implementations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.dalhia.entities.CourseProgress;
import tn.dalhia.repositories.CourseProgressRepository;
import tn.dalhia.services.ICourseProgressService;

import java.util.List;

@Service
@Slf4j
public class CourseProgressService implements ICourseProgressService {

    @Autowired
    CourseProgressRepository courseProgressRepository;

    @Override
    public List<CourseProgress> getAll(){
        return courseProgressRepository.findAll();
    }

    @Override
    public CourseProgress add(CourseProgress courseProgress){
        return courseProgressRepository.save(courseProgress);
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
}
