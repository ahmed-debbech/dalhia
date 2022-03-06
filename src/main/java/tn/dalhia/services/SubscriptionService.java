package tn.dalhia.services;

import java.text.ParseException;

import tn.dalhia.request.SubscriptionRequestModel;
import tn.dalhia.shared.dto.SubscriptionDto;

public interface SubscriptionService {
	SubscriptionDto createSubscription(SubscriptionRequestModel subscription);
	SubscriptionDto updateSubscription(SubscriptionRequestModel subscription, String id);
	SubscriptionDto getSubscriptionById(String id);
	void deleteSubscription(String id);
	void checkSubscription() throws ParseException;
}