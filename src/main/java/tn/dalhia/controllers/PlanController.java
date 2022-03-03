package tn.dalhia.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import tn.dalhia.request.PlanRequestModel;
import tn.dalhia.response.OperationStatusModel;
import tn.dalhia.response.PlanRest;
import tn.dalhia.response.RequestOperationName;
import tn.dalhia.response.RequestOperationStatus;
import tn.dalhia.services.PlanService;
import tn.dalhia.shared.dto.PlanDto;

@RestController
@RequestMapping("plans")
@Api(tags ="Gestion des plans")
public class PlanController {

	@Autowired
	PlanService planService;
	
	@PostMapping()
	public PlanRest createPlan(@RequestBody PlanRequestModel planDetails) {
		PlanRest returnValue = new PlanRest();
		
		ModelMapper modelMapper = new ModelMapper();
		PlanDto createPlan = planService.createPlan(planDetails);
		
		returnValue = modelMapper.map(createPlan, PlanRest.class);
		return returnValue;
	}
	
	@PutMapping("/{id}")
	public PlanRest updatePlan(@RequestBody PlanRequestModel planDetails, @PathVariable Long id) {
		ModelMapper modelMapper = new ModelMapper();
		
		 PlanDto updatePlan = planService.updatePlan(planDetails,id);
		 PlanRest returnValue = modelMapper.map(updatePlan, PlanRest.class);
		return returnValue;
	}
	
	@GetMapping("/{id}")
	public PlanRest getPlan(@PathVariable Long id) {
		ModelMapper modelMapper = new ModelMapper();
		 PlanDto getPlan = planService.getPlanById(id);
		 PlanRest returnValue = modelMapper.map(getPlan, PlanRest.class);
		return returnValue;
	}
	@DeleteMapping("/{id}")
	public OperationStatusModel deletePlan(@PathVariable Long id) {
		OperationStatusModel returnValue = new OperationStatusModel();
		returnValue.setOperationName(RequestOperationName.DELETE.name());
		
		planService.deletePlan(id);
		returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		
		return returnValue;
	}
}
