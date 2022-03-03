package tn.dalhia.services;

import tn.dalhia.entities.CourseCategory;

import java.util.List;

public interface ICourseCategoryService {
    List<CourseCategory> getAll();
    CourseCategory add(CourseCategory courseCategory);
    CourseCategory modify(CourseCategory courseCategory, Long id);
    CourseCategory get(Long id);
    boolean delete(Long id);
}
