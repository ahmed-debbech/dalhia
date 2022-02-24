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

import tn.dalhia.entities.AppointmentRate;
import tn.dalhia.implementations.AppointmentRateService;


@RestController
@RequestMapping("/appointmentRate")
public class AppointementRateController {

	@Autowired
	AppointmentRateService ars;
	
	//http://localhost:8089/Dahlia/appointmentRate/add-appointmentRate/8
	 @PostMapping("/add-appointmentRate/{app-id}")
		public void addAppointmentRate(@RequestBody AppointmentRate Ar,@PathVariable("app-id") Integer AppId) {
		 ars.addAppointmentRate(Ar, AppId);
		}
	 
	//http://localhost:8089/Dahlia/appointmentRate/retrieve-all-appointments-rates
		 @GetMapping("/retrieve-all-appointments-rates")
		 public List <AppointmentRate> getAppointmentsRates(){
			 
			 List <AppointmentRate> listAppRates = ars.getAllAppointmentsRates();
			 return listAppRates;
		 }
		 
	// http://localhost:8089/Dahlia/appointmentRate/remove-appointmentRate/{apprate-id}
		@DeleteMapping("/remove-appointmentRate/{apprate-id}")
		public void removeAppointmentRate(@PathVariable("apprate-id") Long AppRateId) {
			ars.deleteAppointmentRate(AppRateId);
			}

	// http://localhost:8089/Dahlia/appointment/edit-appointmentRate/{apprate-id}
		@PutMapping("/edit-appointmentRate/{apprate-id}")
		public void editAppointment(@RequestBody AppointmentRate aR,@PathVariable("apprate-id") Long AppRateId) {
			 ars.updateAppointmentRate(aR, AppRateId);
			}
}
