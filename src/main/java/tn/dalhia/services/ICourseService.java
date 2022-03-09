package tn.dalhia.services;

import tn.dalhia.entities.Course;
import tn.dalhia.entities.MyCourses;

import java.util.List;

public interface ICourseService {

    List<Course> getAll();
    List<Course> mostRelevantCourse ();
    MyCourses getMyCourses(Long id);
    Course add(Course course, Long id);
    Course modify(Course course, Long id);
    Course get(Long id);
    boolean delete(Long id);
}
