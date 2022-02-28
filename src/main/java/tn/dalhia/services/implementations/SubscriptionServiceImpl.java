package tn.dalhia.services.implementations;



import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.dalhia.entities.Plan;
import tn.dalhia.entities.Subscription;
import tn.dalhia.entities.UserEntity;
import tn.dalhia.exceptions.UserServiceException;
import tn.dalhia.repositories.PlanRepository;
import tn.dalhia.repositories.SubscriptionRepository;
import tn.dalhia.repositories.UserRepository;
import tn.dalhia.request.SubscriptionRequestModel;
import tn.dalhia.response.ErrorMessages;
import tn.dalhia.response.RequestOperationStatus;
import tn.dalhia.services.SubscriptionService;
import tn.dalhia.shared.dto.SubscriptionDto;
import tn.dalhia.shared.dto.Utils;

@Service
public class SubscriptionServiceImpl implements SubscriptionService  {

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	SubscriptionRepository subscriptionRepo;
	
	@Autowired
	PlanRepository planRepo;
	
	@Autowired
	Utils utils;

	@Override
	@Transactional
	public SubscriptionDto createSubscription(SubscriptionRequestModel subscription) {
		UserEntity userEntity = userRepo.findByUserId(subscription.getUserId());
		Plan plan = planRepo.findById(subscription.getPlanId()).orElse(null);
		if (userEntity == null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		if (plan == null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		
		Subscription subEntity = new Subscription();
		BeanUtils.copyProperties(subscription,subEntity);
		subEntity.setSubscritpionId(utils.generateSuscriptionId(30));
		subEntity.setPlans(plan);
		Subscription storedSub = subscriptionRepo.save(subEntity);
		userEntity.setSubscriptions(storedSub); // asigni abonnement lil user hedheka 
		
		SubscriptionDto returnValue = new SubscriptionDto();
		BeanUtils.copyProperties(storedSub,returnValue);
		
		
		
		returnValue.setMessage("l'abonnement du user : "+ subscription.getUserId()+" ---> " + plan.getTitle()+ " commence le : "+ subscription.getDate_debut()+ " et termine le : "+subscription.getDate_fin());
		
		return returnValue;
	}

	@Override
	public SubscriptionDto updateSubscription(SubscriptionRequestModel subscription, String id) {
		SubscriptionDto returnValue = new SubscriptionDto();
		Subscription sub = subscriptionRepo.findBySubscritpionId(id);
		if (sub == null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		
		sub.setDate_debut(subscription.getDate_debut());
		sub.setDate_fin(subscription.getDate_fin());
		
		Subscription updatedSub = subscriptionRepo.save(sub);
		BeanUtils.copyProperties(updatedSub,returnValue);
		returnValue.setMessage(RequestOperationStatus.SUCCESS.name());
		return returnValue;
	}

	@Override
	public SubscriptionDto getSubscriptionById(String id) {
		SubscriptionDto returnValue= new SubscriptionDto();
		Subscription sub = subscriptionRepo.findBySubscritpionId(id);
		
		BeanUtils.copyProperties(sub,returnValue);
		returnValue.setMessage(RequestOperationStatus.SUCCESS.name());
		
		return returnValue;
	}

	@Override
	public void deleteSubscription(String id) {
		Subscription sub = subscriptionRepo.findBySubscritpionId(id);
		if (sub == null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		
		UserEntity user = userRepo.findBySubscriptionsId(sub.getId());
		if (user == null) {
		subscriptionRepo.delete(sub);
		} 
		else {
			subscriptionRepo.delete(sub);
			user.setSubscriptions(null);
			userRepo.save(user);
		}
		
		
	}


}
