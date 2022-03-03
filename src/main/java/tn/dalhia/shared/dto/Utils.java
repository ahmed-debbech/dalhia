package tn.dalhia.shared.dto;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component // khater bch nautowirdiwha
public class Utils {

	private final Random RANDOM = new SecureRandom();
	private final String ALPHABET = "0123456789AZERTYUIOPQSDFGHJKLMWXCVBNazertyuiopqsdfghjklmwxcvbn";
	
	public String generateUserId(int length) {
		return generateRandomString(length);
	}
	
	public String generateSuscriptionId(int length) {
		return generateRandomString(length);
	}
	
	public String generateProductId(int length) {
		return generateRandomString(length);
	}
	
	public String generateCommandId(int length) {
		return generateRandomString(length);
	}
	private String generateRandomString(int length) {
		StringBuilder returnValue = new StringBuilder(length);
		
		for (int i =0; i< length; i++) {
			returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
		}
		
		return new String(returnValue);
	}
}
