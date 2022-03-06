package tn.dalhia.services;

import java.util.List;

import tn.dalhia.entities.Application;



public interface IApplicationService {
	List<Application> retrieveAllApplications();
	List<Application> findAvailableApplications(Long Userid);
	List<Application> findNotAvailableApplications(Long Userid);
	List<Application> AllApplicationSend(Long Userid);

	Application addApplication(Application c);
	public void affecterApplicationAOffer(int idOffer, int idApplication);
	public void affecterApplicationAUser(int idUser, int idApplication);
	void deleteApplication(Long id);

	Application updateApplication(Application c);

	
	Application retrieveApplication(Long id);
}
