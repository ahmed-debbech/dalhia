package tn.dalhia.security;

import tn.dalhia.SpringApplicationContext;

public class SecurityConstants {

	public static final long EXPIRATION_TIME = 3600000  ; // 60 minutes
	public static final String TOKEN_PREFIX = "Bearer " ;
	public static final String HEADER_STRING = "Authorization" ;
	public static final String SIGN_UP_URL = "/users" ;
	public static final String VERIFICATION_EMAIL_URL = "/users/email-verification" ;
	public static final String HELLO = "/users/hello" ;
	public static final String PASSWORD_RESET_REQUEST_URL = "/users/password-reset-request" ;
	public static final String PASSWORD_RESET_URL = "/users/password-reset" ;
	
	public static String getTokenSecret() {
		AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("AppProperties"); //bch njmou naccediw l component tant que manech autowirdiw 
		return appProperties.getTokenSecret();
	}
}
