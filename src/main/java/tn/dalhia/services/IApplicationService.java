package tn.dalhia.services;

import java.util.List;

import tn.dalhia.entities.Application;



public interface IApplicationService {
	List<Application> retrieveAllApplications();

	Application addApplication(Application c);

	void deleteApplication(Long id);

	Application updateApplication(Application c);

	
	Application retrieveApplication(Long id);
}
