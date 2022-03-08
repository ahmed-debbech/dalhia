package tn.dalhia.services.implementations;



import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import tn.dalhia.entities.Plan;
import tn.dalhia.entities.Subscription;
import tn.dalhia.entities.User;
import tn.dalhia.exceptions.UserServiceException;
import tn.dalhia.repositories.PlanRepository;
import tn.dalhia.repositories.SubscriptionRepository;
import tn.dalhia.repositories.UserRepository;
import tn.dalhia.request.SubscriptionRequestModel;
import tn.dalhia.response.ErrorMessages;
import tn.dalhia.response.RequestOperationStatus;
import tn.dalhia.services.SubscriptionService;
import tn.dalhia.shared.dto.SubscriptionDto;
import tn.dalhia.shared.tools.Utils;

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
	public SubscriptionDto createSubscription(SubscriptionRequestModel subscription, Authentication authentification) {
		User userEntity = userRepo.findByUserId(subscription.getUserId());
		Plan plan = planRepo.findById(subscription.getPlanId()).orElse(null);
		
		if(!utils.connectedUser(authentification,userEntity)) throw new UserServiceException(ErrorMessages.SECURITY_ERROR.getErrorMessage());
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
	public SubscriptionDto updateSubscription(SubscriptionRequestModel subscription, String id, Authentication authentification) {
		if(!utils.connectedUser(authentification,null)) throw new UserServiceException(ErrorMessages.SECURITY_ERROR.getErrorMessage());
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
	public SubscriptionDto getSubscriptionById(String id, Authentication authentification) {
		SubscriptionDto returnValue= new SubscriptionDto();
		Subscription sub = subscriptionRepo.findBySubscritpionId(id);
		
		if (sub == null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		if(!utils.connectedUser(authentification,sub.getUserDetails())) throw new UserServiceException(ErrorMessages.SECURITY_ERROR.getErrorMessage());
		
		BeanUtils.copyProperties(sub,returnValue);
		returnValue.setMessage(RequestOperationStatus.SUCCESS.name());
		
		return returnValue;
	}

	@Override
	public void deleteSubscription(String id, Authentication authentification) {
		
		Subscription sub = subscriptionRepo.findBySubscritpionId(id);
		
		if (sub == null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		if(!utils.connectedUser(authentification,sub.getUserDetails())) throw new UserServiceException(ErrorMessages.SECURITY_ERROR.getErrorMessage());
		User user = userRepo.findBySubscriptionsId(sub.getId());
		if (user == null) {
		subscriptionRepo.delete(sub);
		} 
		else {
			subscriptionRepo.delete(sub);
			user.setSubscriptions(null);
			userRepo.save(user);
		}
		
		
	}

	@Override
	//@Scheduled(cron = "0 1 0 * * * ") //kolyoum maa nos lil o d9i9a
	public void checkSubscription() throws ParseException {
		List<Subscription> subs = subscriptionRepo.findAll();
		
		
		Date today = new Date();
		Calendar earlier = Calendar.getInstance();
	    Calendar later = Calendar.getInstance();
	    later.setTime(today);
	
		for(Subscription sub : subs) {
			earlier.setTime(sub.getDate_fin());
			int tempDifference = earlier.get(Calendar.DAY_OF_YEAR) - later.get(Calendar.DAY_OF_YEAR);
			if(tempDifference<=7) {
				
				final String ACCOUNT_SID ="AC228e60b8a2ebb67be77e99883a9ce3fa";
			    final String AUTH_TOKEN = "40e87a192b2f6779dbd40fcc49bace35";
				
				  Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
			        Message message = Message.creator(
			                new com.twilio.type.PhoneNumber("+21654649865"), //to
			                new com.twilio.type.PhoneNumber("+16812525336"), //from
			                "Our Dear client your subscription in our website is about to be expired")
			            .create();

			        System.out.println(message.getBody());
			}
			
		}
		
	}


}
