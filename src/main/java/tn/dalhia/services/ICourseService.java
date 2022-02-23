package tn.dalhia.services;

import tn.dalhia.entities.Course;

import java.util.List;

public interface ICourseService {

    List<Course> retrieveAllClients();

    Course addClient(Course c);

    void deleteClient(Long id);

    Course updateClient(Course c);

    Course retrieveClient(Long id);
}
