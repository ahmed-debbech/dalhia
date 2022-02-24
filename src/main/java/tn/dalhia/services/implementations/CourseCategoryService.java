package tn.dalhia.services.implementations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.dalhia.entities.CourseCategory;
import tn.dalhia.repositories.CourseCategoryRepository;
import tn.dalhia.services.ICourseCategoryService;

import java.util.List;

@Service
@Slf4j
public class CourseCategoryService implements ICourseCategoryService {


    @Autowired
    CourseCategoryRepository courseCategoryRepository;

    @Override
    public List<CourseCategory> getAll(){
        return courseCategoryRepository.findAll();
    }

    @Override
    public CourseCategory add(CourseCategory courseCategory){
        return courseCategoryRepository.save(courseCategory);
    }

    @Override
    public CourseCategory modify(CourseCategory courseCategory , Long id){
        CourseCategory c1 = this.get(id);
        if(c1 == null){
            return null;
        }

        return courseCategoryRepository.save(courseCategory);
    }

    @Override
    public CourseCategory get(Long id){
        return courseCategoryRepository.findById(id).orElse(null);
    }

    @Override
    public  boolean delete(Long id){
        CourseCategory c = courseCategoryRepository.findById(id).orElse(null);
        if(c != null){
            courseCategoryRepository.delete(c);
            return true;
        }
        return false;
    }
}
