package tn.dalhia.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import tn.dalhia.entities.Plan;
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
	
	@PostMapping(produces={MediaType.APPLICATION_JSON_VALUE})
	public PlanRest createPlan(@RequestBody PlanRequestModel planDetails, Authentication authentification) {
		PlanRest returnValue = new PlanRest();
		
		ModelMapper modelMapper = new ModelMapper();
		PlanDto createPlan = planService.createPlan(planDetails,authentification);
		returnValue = modelMapper.map(createPlan, PlanRest.class);
		return returnValue;
	}
	
	@PutMapping(path="/{id}",produces={MediaType.APPLICATION_JSON_VALUE})
	public PlanRest updatePlan(@RequestBody PlanRequestModel planDetails, @PathVariable Long id, Authentication authentification) {
		ModelMapper modelMapper = new ModelMapper();
		
		 PlanDto updatePlan = planService.updatePlan(planDetails,id,authentification);
		 PlanRest returnValue = modelMapper.map(updatePlan, PlanRest.class);
		return returnValue;
	}
	
	@GetMapping(path="/{id}",produces={MediaType.APPLICATION_JSON_VALUE})
	public PlanRest getPlan(@PathVariable Long id, Authentication authentification) {
		ModelMapper modelMapper = new ModelMapper();
		 PlanDto getPlan = planService.getPlanById(id,authentification);
		 PlanRest returnValue = modelMapper.map(getPlan, PlanRest.class);
		return returnValue;
	}
	@DeleteMapping(path="/{id}",produces={MediaType.APPLICATION_JSON_VALUE})
	public OperationStatusModel deletePlan(@PathVariable Long id, Authentication authentification) {
		OperationStatusModel returnValue = new OperationStatusModel();
		returnValue.setOperationName(RequestOperationName.DELETE.name());
		
		planService.deletePlan(id,authentification);
		returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		
		return returnValue;
	}
	@GetMapping(produces={MediaType.APPLICATION_JSON_VALUE})
	public List<PlanRest> getPlans( Authentication authentification) {
		List<Plan> Plans = planService.getPlans(authentification);
		List<PlanRest> returnValue = new ArrayList<>();
		ModelMapper modelMapper = new ModelMapper();
		
		for(Plan plan : Plans) {
			PlanRest p = modelMapper.map(plan, PlanRest.class);
			returnValue.add(p);
		}
		return returnValue;
	}
	
}
