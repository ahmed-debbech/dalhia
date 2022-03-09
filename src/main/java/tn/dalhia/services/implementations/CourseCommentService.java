package tn.dalhia.services.implementations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.dalhia.entities.Course;
import tn.dalhia.entities.CourseCategory;
import tn.dalhia.entities.CourseComment;
import tn.dalhia.repositories.CourseCommentRepository;
import tn.dalhia.repositories.CourseRepository;
import tn.dalhia.services.ICourseCommentService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class CourseCommentService implements ICourseCommentService {

    @Autowired
    CourseCommentRepository courseCommentRepository;
    @Autowired
    CourseRepository courseRepository;

    @Override
    public List<CourseComment> getAll(){
        return courseCommentRepository.findAll();
    }

    @Override
    public CourseComment add(CourseComment courseComment, Long id){
        Course c = courseRepository.findById(id).orElse(null);
        if(c==null)
            return null;
        c.getCourseComments().add(courseComment);
        courseRepository.save(c);
        return courseComment;

    }

    @Override
    public CourseComment modify(CourseComment courseComment , Long id){
        CourseComment c1 = this.get(id);
        if(c1 == null){
            return null;
        }
        c1.setText(courseComment.getText());
        return courseCommentRepository.save(c1);
    }

    @Override
    public CourseComment get(Long id){
        return courseCommentRepository.findById(id).orElse(null);
    }

    @Override
    public  boolean delete(Long id){
        CourseComment c = courseCommentRepository.findById(id).orElse(null);
        if(c != null){
            courseCommentRepository.delete(c);
            return true;
        }
        return false;
    }
}
