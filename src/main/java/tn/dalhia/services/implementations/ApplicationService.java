package tn.dalhia.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.dalhia.entities.Application;
import tn.dalhia.repositories.ApplicationRepository;
import tn.dalhia.services.IApplicationService;

@Service
public class ApplicationService implements IApplicationService {
	@Autowired
	private ApplicationRepository applicationRepo ;

	public List<Application> retrieveAllApplications() {
		return (List<Application>) applicationRepo.findAll();
	}

	public Application addApplication(Application c) {
		return applicationRepo.save(c);
	}

	public void deleteApplication(Long id) {
		applicationRepo.deleteById(id);
		
	}

	public Application updateApplication(Application c) {
		return applicationRepo.save(c);
	}

	public Application retrieveApplication(Long id) {
		return applicationRepo.findById(id).get();
	}
}
