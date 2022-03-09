package tn.dalhia.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.twilio.Twilio;

@Configuration
public class TwilioInit {

	private final TwilioConfiguration twilioConfiguration;
	
	@Autowired
	public TwilioInit(TwilioConfiguration twilioConfiguration){ 
		this.twilioConfiguration = twilioConfiguration;
		Twilio.init(
				twilioConfiguration.getAccountSid(),
				twilioConfiguration.getAuthToken()
				
				);
			
		
	}
	
}
