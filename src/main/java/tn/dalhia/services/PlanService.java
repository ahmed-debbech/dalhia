package tn.dalhia.services;

import java.util.List;

import org.springframework.security.core.Authentication;

import tn.dalhia.entities.Plan;
import tn.dalhia.request.PlanRequestModel;
import tn.dalhia.shared.dto.PlanDto;

public interface PlanService {


	PlanDto createPlan(PlanRequestModel planDetails, Authentication authentification);

	PlanDto updatePlan(PlanRequestModel planDetails, Long id, Authentication authentification);

	PlanDto getPlanById(Long id, Authentication authentification);

	void deletePlan(Long id, Authentication authentification);
	
	List<Plan> getPlans(Authentication authentification);



}
