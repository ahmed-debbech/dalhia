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
	@GetMapping("/retrieve-offer/{app-id}")
	public Application retrieveApplication(@PathVariable("app-id") Long id) {
	return applicationService.retrieveApplication(id);
	}
	
	@PostMapping("/add-application")
	public Application addApplication (@RequestBody Application c) {
	return applicationService.addApplication(c);
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
}
