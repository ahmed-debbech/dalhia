package tn.dalhia.services;

import tn.dalhia.request.PlanRequestModel;
import tn.dalhia.shared.dto.PlanDto;

public interface PlanService {


	PlanDto createPlan(PlanRequestModel planDetails);

	PlanDto updatePlan(PlanRequestModel planDetails, Long id);

	PlanDto getPlanById(Long id);

	void deletePlan(Long id);



}
