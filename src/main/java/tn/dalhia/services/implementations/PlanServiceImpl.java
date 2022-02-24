package tn.dalhia.services.implementations;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.dalhia.entities.Plan;
import tn.dalhia.exceptions.UserServiceException;
import tn.dalhia.repositories.PlanRepository;
import tn.dalhia.request.PlanRequestModel;
import tn.dalhia.response.ErrorMessages;
import tn.dalhia.services.PlanService;
import tn.dalhia.shared.dto.PlanDto;

@Service
public class PlanServiceImpl implements PlanService {

	@Autowired
	PlanRepository planRepo;

	@Override
	public PlanDto createPlan(PlanRequestModel planDetails) {
		Plan planEntity = new Plan();
		PlanDto returnValue = new PlanDto();
		
		BeanUtils.copyProperties(planDetails,planEntity);
		Plan storedPlan = planRepo.save(planEntity); 
		BeanUtils.copyProperties(storedPlan,returnValue);
		
		return returnValue;
	}

	@Override
	public PlanDto updatePlan(PlanRequestModel planDetails, Long id) {
		PlanDto returnValue = new PlanDto();
		Plan plan = planRepo.findById(id).orElse(null);
		if (plan == null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		
		plan.setPhoto(planDetails.getPhoto());
		plan.setPrice(planDetails.getPrice());
		plan.setTitle(planDetails.getTitle());
		
		Plan updatePlan = planRepo.save(plan);
		BeanUtils.copyProperties(updatePlan,returnValue);
		return returnValue;
	}

	@Override
	public PlanDto getPlanById(Long id) {
		PlanDto returnValue = new PlanDto();
		Plan plan = planRepo.findById(id).orElse(null);
		if (plan == null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		BeanUtils.copyProperties(plan,returnValue);
		return returnValue;
	}

	@Override
	public void deletePlan(Long id) {
		Plan plan = planRepo.findById(id).orElse(null);
		if (plan == null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		planRepo.delete(plan);
		
		
	}

}
