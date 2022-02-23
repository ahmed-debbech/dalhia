package tn.dalhia.services.implementations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.dalhia.entities.Course;
import tn.dalhia.repositories.CourseRepository;
import tn.dalhia.services.ICourseService;
import java.util.List;

@Service
@Slf4j
public class CourseService implements ICourseService {

    @Autowired
    CourseRepository courseRepository;

    @Override
    public List<Course> retrieveAllClients(){
        List<Course> listCourse= courseRepository.findAll();
        for(Course c:listCourse)
        {
            log.info("Course:" + c.getName() );
        }
        return listCourse;
    }

    @Override
    public Course addClient(Course c) {
        courseRepository.save(c);
        return c;
    }

    @Override
    public void deleteClient(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public Course updateClient(Course c) {
        courseRepository.save(c);
        return c;
    }

    @Override
    public Course retrieveClient(Long id) {
        Course c= courseRepository.findById(id).get();
        return c;
    }
}
