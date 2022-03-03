package tn.dalhia.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
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
	
	@PostMapping()
	public SubscriptionRest createSubscription (@RequestBody SubscriptionRequestModel subscription) {
		ModelMapper modelMapper = new ModelMapper();
		
		SubscriptionDto createdSubscription = subscriptionService.createSubscription(subscription)	;
		SubscriptionRest returnValue = modelMapper.map(createdSubscription, SubscriptionRest.class);
		return returnValue;
	}
	
	@PutMapping("/{id}")
	public SubscriptionRest updateSubscription (@PathVariable String id ,@RequestBody SubscriptionRequestModel subscription ) {
		ModelMapper modelMapper = new ModelMapper();
		
		
		SubscriptionDto updateSub = subscriptionService.updateSubscription(subscription, id);
		SubscriptionRest returnValue = modelMapper.map(updateSub, SubscriptionRest.class);
		
		return returnValue;
	}
	
	@GetMapping("/{id}")
	public SubscriptionRest getSubscription (@PathVariable String id) {
		SubscriptionRest returnValue = new SubscriptionRest();
		SubscriptionDto Subscription = subscriptionService.getSubscriptionById(id);
		BeanUtils.copyProperties(Subscription, returnValue);
		
		return returnValue;
	}
	@DeleteMapping("/{id}")
	public OperationStatusModel deleteSubscription(@PathVariable String id) {
		OperationStatusModel returnValue = new OperationStatusModel();
		returnValue.setOperationName(RequestOperationName.DELETE.name());
		
		subscriptionService.deleteSubscription(id);
		returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		
		return returnValue;
	}
}
