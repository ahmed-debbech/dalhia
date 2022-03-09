package tn.dalhia.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.dalhia.entities.Application;
import tn.dalhia.services.IApplicationService;

@RestController
@RequestMapping("/Application")
public class ApplicationController {

	@Autowired
	IApplicationService applicationService;

	// http://localhost:8089/SpringMVC/application/retrieve-all-applications
	@GetMapping("/retrieve-all-applications")
	public List<Application> getApplications() {
	List<Application> listApplications = applicationService.retrieveAllApplications();
	return listApplications;
	}

	// http://localhost:8089/SpringMVC/offer/retrieve-offer/8
	@GetMapping("/retrieve-application/{app-id}")
	public Application retrieveApplication(@PathVariable("app-id") Long id) {
	return applicationService.retrieveApplication(id);
	}
	
	@PostMapping("/add-application/{offer-id}/{user-id}")
	public boolean addApplication (@PathVariable("offer-id") int Offer_id,@PathVariable("user-id") int user_id,@RequestBody Application c) {
		Application ap1 = applicationService.addApplication(c);
		applicationService.affecterApplicationAOffer(Offer_id,ap1.getId().intValue());
		applicationService.affecterApplicationAUser(user_id,ap1.getId().intValue());
		return true;
	}

	// http://localhost:8089/SpringMVC/Offer/remove-Offer/{Offer-id}
	@DeleteMapping("/remove-application/{application-id}")
	public void removeApplication(@PathVariable("application-id") Long id) {
		applicationService.deleteApplication(id);
	}

	// http://localhost:8089/SpringMVC/offer/modify-offer
	@PutMapping("/modify-application")
	public Application modifyApplication(@RequestBody Application app) {
	return applicationService.updateApplication(app);
	}

	@GetMapping("/applications/{user-id}")
	public List<Application> getApplications(@PathVariable("user-id") Long userid) {
		List<Application> listApplications = applicationService.AllApplicationSend(userid);
		return listApplications;
	}

	@GetMapping("/applicationsAvailable/{user-id}")
	public List<Application> getApplicationsAvailable(@PathVariable("user-id") Long userid) {
		List<Application> listApplications = applicationService.findAvailableApplications(userid);
		return listApplications;
	}

	@GetMapping("/applicationsNotAvailable/{user-id}")
	public List<Application> getApplicationsNotAvailable(@PathVariable("user-id") Long userid) {
		List<Application> listApplications = applicationService.findNotAvailableApplications(userid);
		return listApplications;
	}

	@PostMapping("/ValidCondidat/{application-id}")
	public void ValidCondidat(@PathVariable("application-id") Long applicationid) {
		applicationService.ValidCondidat(applicationid);

	}
	@PostMapping("/InValidCondidat/{application-id}")
	public void InValidCondidat(@PathVariable("application-id") Long applicationid) {
		applicationService.InValidCondidat(applicationid);

	}
}
