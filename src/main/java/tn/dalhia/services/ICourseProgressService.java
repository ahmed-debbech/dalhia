package tn.dalhia.services;

import tn.dalhia.entities.CourseProgress;

import java.util.List;

public interface ICourseProgressService {
    List<CourseProgress> getAll();
    CourseProgress add(CourseProgress courseProgress,Long id, Long idUser);
    CourseProgress get(Long id);
    CourseProgress modify(Long idC, Long id,int endPhase);
    boolean delete(Long id);
}