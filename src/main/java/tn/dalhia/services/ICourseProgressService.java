package tn.dalhia.services;

import tn.dalhia.entities.CourseProgress;

import java.util.List;

public interface ICourseProgressService {
    List<CourseProgress> getAll();
    CourseProgress add(CourseProgress courseProgress);
    CourseProgress get(Long id);
    boolean delete(Long id);
}