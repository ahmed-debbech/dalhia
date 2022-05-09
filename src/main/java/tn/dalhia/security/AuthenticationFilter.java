package tn.dalhia.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import tn.dalhia.SpringApplicationContext;
import tn.dalhia.exceptions.UserServiceException;
import tn.dalhia.request.UserLoginRequestModel;
import tn.dalhia.response.ErrorMessages;
import tn.dalhia.services.UserService;
import tn.dalhia.shared.dto.UserDto;

@CrossOrigin(origins = "*")
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	private final AuthenticationManager authenticationManager;

	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	@Override
	 
	public Authentication attemptAuthentication(HttpServletRequest req,HttpServletResponse res) throws AuthenticationException {
		try {
			UserLoginRequestModel creds = new ObjectMapper().readValue(req.getInputStream(),UserLoginRequestModel.class);
			
			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							creds.getEmail(),creds.getPassword(), // spring framework yaaml kol chay yodkhel lil base o ilawj ala email o password taa creds
							new ArrayList<>()));
		} catch(IOException e) {
			throw new UserServiceException(ErrorMessages.AUTHENTICTION_FAILED.getErrorMessage());
		}
	}
	
	@Override
	public void successfulAuthentication(HttpServletRequest req,
										HttpServletResponse res,
										FilterChain chain,
										Authentication auth) throws IOException, ServletException {
		String userName = ((User) auth.getPrincipal()).getUsername();
		
		String token = Jwts.builder()
				.setSubject(userName)
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512,SecurityConstants.getTokenSecret())
				.compact();
																									//khater authenticationfilter mahich bean donc manjmou autowirdiw chay
	    UserService userService = (UserService)SpringApplicationContext.getBean("userServiceImpl"); // nestaamlou springapplicationcontext bch njmou njibou userimpl bean bch njmou nkharjou user details
		UserDto userDto = userService.getUser(userName);
		 
		res.setHeader("Access-Control-Allow-Origin", "*");
	    res.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
	    res.setHeader("Access-Control-Max-Age", "3600");
	    res.setHeader("Access-Control-Allow-Headers", "Authorization, x-xsrf-token, Access-Control-Allow-Headers, Origin, Accept, X-Requested-With, " +
	            "Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");
		//res.addHeader(SecurityConstants.HEADER_STRING,SecurityConstants.TOKEN_PREFIX+ token); // token ki djina fil header
		res.addHeader("UserId",userDto.getUserId()); //bch nwaliw nraw user id ki yaaml sign up
		res.addHeader("Role",userDto.getRole().name());
		res.setHeader(SecurityConstants.HEADER_STRING,SecurityConstants.TOKEN_PREFIX+ token);
		
	}
}