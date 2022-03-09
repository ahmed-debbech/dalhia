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
import tn.dalhia.entities.Request;
import tn.dalhia.implementations.AppointmentService;
import tn.dalhia.implementations.RequestService;

@RestController
@RequestMapping("/request")
public class RequestController {

	@Autowired
	RequestService rqs;
	
	//http://localhost:8089/Dahlia/request/add-request/8
	 @PostMapping("/add-request/{expert-id}")
		public void addRequest(@RequestBody Request rq,@PathVariable("expert-id") Long ExpertId) {
		 rqs.addRequest(rq, ExpertId);
		}
	 
	//http://localhost:8089/Dahlia/request/retrieve-all-requests
		 @GetMapping("/retrieve-all-requests")
		 public List <Request> getRequests(){
			 
			 List <Request> listRqs = rqs.getAllRequests();
			 return listRqs;
		 }
		 
	// http://localhost:8089/Dahlia/request/remove-request/{rq-id}
		@DeleteMapping("/remove-request/{rq-id}")
		public void removeRequest(@PathVariable("rq-id") Integer RqId) {
			rqs.deleteRequest(RqId);
			}

	// http://localhost:8089/Dahlia/request/edit-request/{rq-id}
		@PutMapping("/edit-request/{rq-id}")
		public void editRequest(@RequestBody Request rq,@PathVariable("rq-id") Integer RqId) {
			 rqs.updateRequest(rq, RqId);
			}
}
