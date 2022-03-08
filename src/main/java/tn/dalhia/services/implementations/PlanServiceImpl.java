package tn.dalhia.services.implementations;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import tn.dalhia.entities.Plan;
import tn.dalhia.exceptions.UserServiceException;
import tn.dalhia.repositories.PlanRepository;
import tn.dalhia.request.PlanRequestModel;
import tn.dalhia.response.ErrorMessages;
import tn.dalhia.services.PlanService;
import tn.dalhia.shared.dto.PlanDto;
import tn.dalhia.shared.tools.Utils;

@Service
public class PlanServiceImpl implements PlanService {

	@Autowired
	PlanRepository planRepo;
	
	@Autowired
	Utils utils;

	@Override
	public PlanDto createPlan(PlanRequestModel planDetails, Authentication authentification) {
		
		Plan planEntity = new Plan();
		PlanDto returnValue = new PlanDto();
		if(!utils.connectedUser(authentification,null)) throw new UserServiceException(ErrorMessages.SECURITY_ERROR.getErrorMessage());
		BeanUtils.copyProperties(planDetails,planEntity);
		Plan storedPlan = planRepo.save(planEntity); 
		BeanUtils.copyProperties(storedPlan,returnValue);
		
		return returnValue;
	}

	@Override
	public PlanDto updatePlan(PlanRequestModel planDetails, Long id, Authentication authentification) {
		
		if(!utils.connectedUser(authentification,null)) throw new UserServiceException(ErrorMessages.SECURITY_ERROR.getErrorMessage());
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
	public PlanDto getPlanById(Long id, Authentication authentification) {
		if(!utils.connectedUser(authentification,null)) throw new UserServiceException(ErrorMessages.SECURITY_ERROR.getErrorMessage());
		PlanDto returnValue = new PlanDto();
		Plan plan = planRepo.findById(id).orElse(null);
		if (plan == null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		BeanUtils.copyProperties(plan,returnValue);
		return returnValue;
	}

	@Override
	public void deletePlan(Long id, Authentication authentification) {
		if(!utils.connectedUser(authentification,null)) throw new UserServiceException(ErrorMessages.SECURITY_ERROR.getErrorMessage());
		Plan plan = planRepo.findById(id).orElse(null);
		if (plan == null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		planRepo.delete(plan);
		
		
	}

}
