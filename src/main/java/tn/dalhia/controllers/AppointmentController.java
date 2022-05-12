package tn.dalhia.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.dalhia.entities.Appointment;
import tn.dalhia.entities.AppointmentRate;
import tn.dalhia.entities.AppointmentReport;
import tn.dalhia.entities.User;
import tn.dalhia.implementations.AppointmentService;





@RestController
@RequestMapping("/appointment")
public class AppointmentController {

	@Autowired
	AppointmentService as;
	
	//http://localhost:8089/Dahlia/appointment/add-appointment/8
	 @CrossOrigin(origins="*")
	 @PostMapping("/add-appointment/{expert-id}")
		public void addAppointment(@RequestBody Appointment app,@PathVariable("expert-id") Long ExpertId) {
		 as.addAppointment(app, ExpertId);
		}
	 
	//http://localhost:8089/Dahlia/appointment/retrieve-all-appointments
	     @CrossOrigin(origins="*")
		 @GetMapping("/retrieve-all-appointments")
		 public List <Appointment> getAppointments(){
			 
			 List <Appointment> listApps = as.getAllAppointments();
			 return listApps;
		 }
	     
	   //http://localhost:8089/Dahlia/appointment/retrieve-all-appointments
	     @CrossOrigin(origins="*")
		 @GetMapping("/retrieve-my-appointments/{sender-id}")
		 public List <Appointment> getMyAppointments(@PathVariable ("sender-id")Long senderId){
			 
			 List <Appointment> listApps = as.getMyAppointments(senderId);
			 return listApps;
		 }
	     
	     @CrossOrigin(origins="*")
		 @GetMapping("/retrieve-app-reports/{appReport-id}")
		 public  List<AppointmentReport> getAppReport(@PathVariable ("appReport-id")Long appReportId){
			 
			 
			 return as.getAppointmentReports(appReportId);
		 }
	     
	     @CrossOrigin(origins="*")
		 @GetMapping("/retrieve-app-rates/{appRate-id}")
		 public  List<AppointmentRate> getAppRate(@PathVariable ("appRate-id")Long appRateId){
			 
			 
			 return as.getAppointmentRates(appRateId);
		 }
	     
	     @CrossOrigin(origins="*")
		 @GetMapping("/retrieve-all-experts")
		 public List <User> getExperts(){
			 
			 List <User> Exps = as.getExperts();
			 return Exps;
		 }
		 
		//http://localhost:8089/Dahlia/review/retrieve-all-reviews
	     @CrossOrigin(origins="*")
		 @GetMapping("/retrieve-most&least-visited-expert")
		 public void retrieveMostAndLeastVisitedExpert(){
	    	 as.getMostAndLeastVisitedExpert();
		 }
	     
	   //http://localhost:8089/Dahlia/review/retrieve-all-reviews
	     @CrossOrigin(origins="*")
		 @GetMapping("/retrieve-least-visited-expert")
		 public User retrieveLeastVisitedExpert(){
			 
			 return as.getLeastExpert();
		 }
	     
	   //http://localhost:8089/Dahlia/review/retrieve-all-reviews
	     @CrossOrigin(origins="*")
		 @GetMapping("/retrieve-most-visited-expert")
		 public User retrieveMostVisitedExpert(){
			 return as.getMostExpert();
		 }
	     
	   //http://localhost:8089/Dahlia/review/retrieve-all-reviews
	     @CrossOrigin(origins="*")
		 @GetMapping("/retrieve-expert-details/{expert-id}")
		 public User getExpertDetails(@PathVariable ("expert-id")Long expertId){
			 return as.getExpertDetails(expertId);
		 }
		 
		//http://localhost:8089/Dahlia/review/retrieve-all-reviews
	     @CrossOrigin(origins="*")
		 @GetMapping("/ban-reported-expert")
		 public User banReportedExpert(){
			 return as.banReportedExpert();
		 }
		 
	// http://localhost:8089/Dahlia/appointment/remove-appointment/{app-id}
		@CrossOrigin(origins="*")
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
