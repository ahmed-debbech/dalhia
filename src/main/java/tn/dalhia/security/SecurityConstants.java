package tn.dalhia.security;

import tn.dalhia.SpringApplicationContext;

public class SecurityConstants {

	public static final long EXPIRATION_TIME = 3600000  ; // 60 minutes
	public static final String TOKEN_PREFIX = "Bearer " ;
	public static final String HEADER_STRING = "Authorization" ;
	public static final String SIGN_UP_URL = "/users" ;
	
	public static String getTokenSecret() {
		AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("AppProperties"); //bch njmou naccediw l component tant que manech autowirdiw 
		return appProperties.getTokenSecret();
	}
}
