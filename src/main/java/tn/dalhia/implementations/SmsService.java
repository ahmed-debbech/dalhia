package tn.dalhia.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import lombok.extern.slf4j.Slf4j;
import tn.dalhia.configurations.TwilioConfiguration;
import tn.dalhia.entities.User;

@Slf4j
@Service
public class SmsService {
	
	private final TwilioConfiguration twilioConfiguration;
	
	@Autowired
	public SmsService(TwilioConfiguration twilioConfiguration){
		this.twilioConfiguration = twilioConfiguration;
	}

	public void sendSms(User user){
		/*if(isPhoneNumberValid(user.getPhone())){*/
				PhoneNumber to = new PhoneNumber("+216"+user.getPhone());
				PhoneNumber from = new PhoneNumber("+15617638595");
				String sms = "You have recieved an appointment notification, please check your account at Dahlia App for more details.";
				MessageCreator creator = Message.creator(to, from, sms);
				creator.create();
				log.info("SMS sending processing...");
				/*}else
				{
					log.info("Phone number invalid.");
				}*/
	}
	
	public void sendWarningSms(User user){
		/*if(isPhoneNumberValid(user.getPhone())){*/
				PhoneNumber to = new PhoneNumber(user.getPhone());
				PhoneNumber from = new PhoneNumber("+15617638595");
				String sms = "You have recieved a BAN warning at DAHLIA App due to an appointment report assigned by a user.";
				MessageCreator creator = Message.creator(to, from, sms);
				creator.create();
				log.info("SMS sending processing...");
				/*}else
				{
					log.info("Phone number invalid.");
				}*/
	}
	
	public void sendBanSms(User user){
		/*if(isPhoneNumberValid(user.getPhone())){*/
				PhoneNumber to = new PhoneNumber(user.getPhone());
				PhoneNumber from = new PhoneNumber("+15617638595");
				String sms = "You have been BANNED by Admin at DAHLIA App due to excessive appointment reports by users.";
				MessageCreator creator = Message.creator(to, from, sms);
				creator.create();
				log.info("SMS sending processing...");
				/*}else
				{
					log.info("Phone number invalid.");
				}*/
	}
}
