package tn.dalhia.services;

import tn.dalhia.entities.CourseComment;

import java.util.List;

public interface ICourseCommentService {

    List<CourseComment> getAll();
    CourseComment add(CourseComment courseComment);
    CourseComment modify(CourseComment courseComment, Long id);
    CourseComment get(Long id);
    boolean delete(Long id);
}
