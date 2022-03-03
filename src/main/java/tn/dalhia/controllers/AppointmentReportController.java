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

import tn.dalhia.entities.AppointmentReport;
import tn.dalhia.implementations.AppointmentReportService;

@RestController
@RequestMapping("/appointmentReport")
public class AppointmentReportController {

	@Autowired
	AppointmentReportService ars;
	
	//http://localhost:8089/Dahlia/appointmentReport/add-appointmentReport/8
	 @PostMapping("/add-appointmentReport/{app-id}")
		public void addAppointmentReport(@RequestBody AppointmentReport Ar,@PathVariable("app-id") Integer AppId) {
		 ars.addAppointmentReport(Ar, AppId);
		}
	 
	//http://localhost:8089/Dahlia/appointmentReport/retrieve-all-appointments-reports
		 @GetMapping("/retrieve-all-appointments-reports")
		 public List <AppointmentReport> getAppointmentsReports(){
			 
			 List <AppointmentReport> listAppRprts = ars.getAllAppointmentsReports();
			 return listAppRprts;
		 }
		 
	// http://localhost:8089/Dahlia/appointmentReport/remove-appointmentReport/{apprp-id}
		@DeleteMapping("/remove-appointmentReport/{apprp-id}")
		public void removeAppointmentRate(@PathVariable("apprp-id") Long AppRpId) {
			ars.deleteAppointmentReport(AppRpId);
			}

	// http://localhost:8089/Dahlia/appointmentReport/edit-appointmentReport/{apprp-id}
		@PutMapping("/edit-appointmentReport/{apprp-id}")
		public void editAppointment(@RequestBody AppointmentReport aR,@PathVariable("apprp-id") Long AppRpId) {
			 ars.updateAppointmentReport(aR, AppRpId);
			}
}
