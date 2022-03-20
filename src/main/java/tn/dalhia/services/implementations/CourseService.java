package tn.dalhia.services.implementations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tn.dalhia.entities.*;
import tn.dalhia.entities.enumerations.CourseStatus;
import tn.dalhia.repositories.*;
import tn.dalhia.services.ICourseService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.springframework.data.domain.Sort.Direction.ASC;

@Service
@Slf4j
public class CourseService implements ICourseService {

    @Autowired
    CourseRepository courseRepository;
    @Autowired
    CourseCategoryRepository courseCategoryRepository;
    @Autowired
    CertificateRepository certificateRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CourseProgressRepository courseProgressRepository;

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

    @Override
    public MyCourses getMyCourses (Long id){
        MyCourses myCourses = new MyCourses();
        List<Course> FCWithCertificates = new ArrayList<>();
        List<Course> FCWithoutCertificates = new ArrayList<>();
        List<Course> coursesInProgress = new ArrayList<>();
        User user = userRepository.findById(id).orElse(null);

        List<Certificate> certificates = certificateRepository.findAll();
        for (Certificate certificate: certificates) {
            if (certificate.getUser().getId() == user.getId()) {
                FCWithCertificates.add(certificate.getCourse());
            }
        }
        List<CourseProgress> UCP = user.getCourseProgresses();
        for (CourseProgress courseProgress: UCP){
            if (courseProgress.getCourseProgressStatus().equals("F")){// status=1 maaneha course "FINISHED"
                FCWithoutCertificates.add(courseProgress.getCourse());
            }
            else {
                coursesInProgress.add(courseProgress.getCourse());
            }
        }
        myCourses.setCoursesInProgress(coursesInProgress);
        myCourses.setFCWithoutCertificates(FCWithoutCertificates);
        myCourses.setFCWithCertificates(FCWithCertificates);
        return myCourses;
    }

    @Override
    public List<Course> mostRelevantCourse (){
        List<Course> mrc = new ArrayList<>();
        for (CourseCategory cc : courseCategoryRepository.findAll()){
            Collections.sort(cc.getCourseList());
            mrc.add(cc.getCourseList().get(0));
        }
        return mrc;
    }

    @Override
    public List<Course> acceptedCourses(){
        List<Course> AC = new ArrayList<>();
        for (Course c : courseRepository.findAll()){
            if (c.getCourseStatus().equals(CourseStatus.ACCEPTED)){
                AC.add(c);
            }
        }
        return  AC;
    }

    @Override
    public List<Course> rejectedCourses(){
        List<Course> RC = new ArrayList<>();
        for (Course c : courseRepository.findAll()){
            if (c.getCourseStatus().equals(CourseStatus.REJECTED)){
                RC.add(c);
            }
        }
        return  RC;
    }
}
