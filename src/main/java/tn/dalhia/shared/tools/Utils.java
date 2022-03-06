package tn.dalhia.shared.tools;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import tn.dalhia.security.SecurityConstants;


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


	public static boolean hastokenExpired(String token) {
		Claims claims = Jwts.parser().setSigningKey(SecurityConstants.getTokenSecret()).parseClaimsJws(token).getBody();

		Date tokenExpirationDate = claims.getExpiration();
		Date todayDate = new Date();
		return tokenExpirationDate.before(todayDate);
	}
	
	public static boolean hastokenExpired2(String token) {
		DecodedJWT jwt = JWT.decode(token);
		if( jwt.getExpiresAt().before(new Date())) {
		    return true;
		}
		return false;
	}

	public String generateEmailVerificationToken(String publicUserId) {
		String token = Jwts.builder()
				.setSubject(publicUserId)
				.setExpiration(new Date(System.currentTimeMillis()+ SecurityConstants.EXPIRATION_TIME_EMAIL))
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret()).compact();

		return token;
	}
	public String generatePasswordResetToken(String userId) {
		String token = Jwts.builder()
				.setSubject(userId)
				.setExpiration(new Date(System.currentTimeMillis()+ SecurityConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret()).compact();

		return token;
	}

}
