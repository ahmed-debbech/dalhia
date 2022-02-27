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
import tn.dalhia.entities.Report;
import tn.dalhia.implementations.AppointmentService;
import tn.dalhia.implementations.ReportService;

@RestController
@RequestMapping("/report")
public class ReportController {

	@Autowired
	ReportService rs;
	
	//http://localhost:8089/Dahlia/report/add-report
	 @PostMapping("/add-report")
		public void addReport(@RequestBody Report rp) {
		 rs.addReport(rp);
		}
	 
	//http://localhost:8089/Dahlia/report/retrieve-all-reports
		 @GetMapping("/retrieve-all-reports")
		 public List <Report> getReports(){
			 
			 List <Report> listRps = rs.getAllReports();
			 return listRps;
		 }
		 
	// http://localhost:8089/Dahlia/report/remove-report/{rp-id}
		@DeleteMapping("/remove-report/{rp-id}")
		public void removeReport(@PathVariable("rp-id") Integer RpId) {
			rs.deleteReport(RpId);
			}

	// http://localhost:8089/Dahlia/report/edit-report/{rp-id}
		@PutMapping("/edit-report/{rp-id}")
		public void editReport(@RequestBody Report rp,@PathVariable("rp-id") Integer RpId) {
			 rs.updateReport(rp, RpId);
			}
		
	// http://localhost:8089/Dahlia/report/manage-report-status/{rp-id}
		@PutMapping("/manage-report-status/{rp-id}")
		public void manageReportStatus(@RequestBody Report rp,@PathVariable("rp-id") Integer RpId) {
					 rs.manageReportStatus(rp, RpId);
					}
}
