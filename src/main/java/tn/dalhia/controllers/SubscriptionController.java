package tn.dalhia.controllers;

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
import tn.dalhia.request.SubscriptionRequestModel;
import tn.dalhia.response.OperationStatusModel;
import tn.dalhia.response.RequestOperationName;
import tn.dalhia.response.RequestOperationStatus;
import tn.dalhia.response.SubscriptionRest;
import tn.dalhia.services.SubscriptionService;
import tn.dalhia.shared.dto.SubscriptionDto;

@RestController
@RequestMapping("subscriptions")
@Api(tags ="Gestion des abonnements")
public class SubscriptionController {

	@Autowired
	SubscriptionService subscriptionService;
	
	@PostMapping(produces={MediaType.APPLICATION_JSON_VALUE})
	public SubscriptionRest createSubscription (@RequestBody SubscriptionRequestModel subscription, Authentication authentification) {
		SubscriptionRest returnValue = new SubscriptionRest();
		
		SubscriptionDto createdSubscription = subscriptionService.createSubscription(subscription,authentification)	;
		BeanUtils.copyProperties(createdSubscription, returnValue);
		return returnValue;
	}
	
	@PutMapping(path="/{id}",produces={MediaType.APPLICATION_JSON_VALUE})
	public SubscriptionRest updateSubscription (@PathVariable String id ,@RequestBody SubscriptionRequestModel subscription, Authentication authentification ) {
		SubscriptionRest returnValue = new SubscriptionRest();
		
		
		SubscriptionDto updateSub = subscriptionService.updateSubscription(subscription, id,authentification);
		BeanUtils.copyProperties(updateSub, returnValue);
		
		return returnValue;
	}
	
	@GetMapping(path="/{id}",produces={MediaType.APPLICATION_JSON_VALUE})
	public SubscriptionRest getSubscription (@PathVariable String id, Authentication authentification) {
		SubscriptionRest returnValue = new SubscriptionRest();
		SubscriptionDto Subscription = subscriptionService.getSubscriptionById(id,authentification);
		BeanUtils.copyProperties(Subscription, returnValue);
		
		return returnValue;
	}
	@DeleteMapping(path="/{id}",produces={MediaType.APPLICATION_JSON_VALUE})
	public OperationStatusModel deleteSubscription(@PathVariable String id, Authentication authentification) {
		OperationStatusModel returnValue = new OperationStatusModel();
		returnValue.setOperationName(RequestOperationName.DELETE.name());
		
		subscriptionService.deleteSubscription(id,authentification);
		returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		
		return returnValue;
	}
	
	@GetMapping(produces={MediaType.APPLICATION_JSON_VALUE})
	public List<SubscriptionRest> getSubscriptions ( Authentication authentification) {
		return subscriptionService.getSubscriptions(authentification);
	}
}
