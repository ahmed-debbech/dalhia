package tn.dalhia.services.implementations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.dalhia.entities.Course;
import tn.dalhia.repositories.CourseRepository;
import tn.dalhia.services.ICourseService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class CourseService implements ICourseService {

    @Autowired
    CourseRepository courseRepository;

    @Override
    public List<Course> getAll(){
        return courseRepository.findAll();
    }

    @Override
    public Course add(Course course){
        course.setDateAdded(LocalDateTime.now());
        course.setDatePublished(LocalDateTime.now());
        return courseRepository.save(course);
    }

    @Override
    public Course modify(Course course , Long id){
        Course c1 = this.get(id);
        if(c1 == null){
            return null;
        }

        return courseRepository.save(course);
    }

    @Override
    public Course get(Long id){
        return courseRepository.findById(id).orElse(null);
    }

    @Override
    public  boolean delete(Long id){
        Course c = courseRepository.findById(id).orElse(null);
        if(c != null){
            courseRepository.delete(c);
            return true;
        }
        return false;
    }
}
