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

import tn.dalhia.entities.Appointment;
import tn.dalhia.implementations.AppointmentService;





@RestController
@RequestMapping("/appointment")
public class AppointmentController {

	@Autowired
	AppointmentService as;
	
	//http://localhost:8089/Dahlia/appointment/add-appointment/8
	 @PostMapping("/add-appointment/{expert-id}")
		public void addAppointment(@RequestBody Appointment app,@PathVariable("expert-id") Long ExpertId) {
		 as.addAppointment(app, ExpertId);
		}
	 
	//http://localhost:8089/Dahlia/appointment/retrieve-all-appointments
		 @GetMapping("/retrieve-all-appointments")
		 public List <Appointment> getAppointments(){
			 
			 List <Appointment> listApps = as.getAllAppointments();
			 return listApps;
		 }
		 
	// http://localhost:8089/Dahlia/appointment/remove-appointment/{app-id}
		@DeleteMapping("/remove-appointment/{app-id}")
		public void removeAppointment(@PathVariable("app-id") Integer AppId) {
			as.deleteAppointment(AppId);
			}

	// http://localhost:8089/Dahlia/appointment/edit-appointment/{app-id}
		@PutMapping("/edit-appointment/{app-id}")
		public void editAppointment(@RequestBody Appointment app,@PathVariable("app-id") Integer AppId) {
			 as.updateAppointment(app, AppId);
			}
}
