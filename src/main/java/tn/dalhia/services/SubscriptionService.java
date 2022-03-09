package tn.dalhia.services;

import java.text.ParseException;

import org.springframework.security.core.Authentication;


import tn.dalhia.request.SubscriptionRequestModel;
import tn.dalhia.shared.dto.SubscriptionDto;

public interface SubscriptionService {
	SubscriptionDto createSubscription(SubscriptionRequestModel subscription, Authentication authentification);
	SubscriptionDto updateSubscription(SubscriptionRequestModel subscription, String id, Authentication authentification);
	SubscriptionDto getSubscriptionById(String id, Authentication authentification);
	void deleteSubscription(String id, Authentication authentification);
	void checkSubscription() throws ParseException;
}
