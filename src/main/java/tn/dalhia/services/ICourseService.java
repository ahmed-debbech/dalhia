package tn.dalhia.services;

import tn.dalhia.entities.Course;

import java.util.List;

public interface ICourseService {

    List<Course> getAll();
    Course add(Course course, Long id);
    Course modify(Course course, Long id);
    Course get(Long id);
    boolean delete(Long id);
}
