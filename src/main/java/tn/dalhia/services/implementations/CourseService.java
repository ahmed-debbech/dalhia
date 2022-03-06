package tn.dalhia.services.implementations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.dalhia.entities.Course;
import tn.dalhia.entities.CourseCategory;
import tn.dalhia.repositories.CourseCategoryRepository;
import tn.dalhia.repositories.CourseRepository;
import tn.dalhia.services.ICourseService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class CourseService implements ICourseService {

    @Autowired
    CourseRepository courseRepository;
    @Autowired
    CourseCategoryRepository courseCategoryRepository;

    @Override
    public List<Course> getAll(){
        return courseRepository.findAll();
    }

    @Override
    public Course add(Course course, Long id){
        CourseCategory cc = courseCategoryRepository.findById(id).orElse(null);
        if(cc==null)
            return null;
        course.setDateAdded(LocalDateTime.now());
        course.setDatePublished(LocalDateTime.now());
        cc.getCourseList().add(course);
        courseCategoryRepository.save(cc);
        return course;
    }

    @Override
    public Course modify(Course course , Long id){
        Course c1 = this.get(id);
        if(c1 == null){
            return null;
        }
        c1.setName(course.getName());
        c1.setModality(course.getModality());
        c1.setPlaces(course.getPlaces());
        c1.setPrice(course.getPrice());
        c1.setPhases(course.getPhases());
        return courseRepository.save(c1);
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
