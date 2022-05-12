package tn.dalhia.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import tn.dalhia.services.UserService;





@EnableWebSecurity
@Configuration
public class WebSecurity extends WebSecurityConfigurerAdapter{

	private final UserService userDetailsService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public WebSecurity(UserService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.userDetailsService = userDetailsService;
	}
	
	@Override
	protected void configure (HttpSecurity http) throws Exception {
		 http.cors().and()
         .authorizeRequests()
         .antMatchers(HttpMethod.OPTIONS).permitAll();
		http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST , SecurityConstants.SIGN_UP_URL)
		.permitAll()
		.antMatchers(HttpMethod.GET , SecurityConstants.VERIFICATION_EMAIL_URL)
		.permitAll()
		.antMatchers(HttpMethod.GET , SecurityConstants.GET_PRODUCTS)
		.permitAll()
		.antMatchers(HttpMethod.POST , SecurityConstants.PASSWORD_RESET_REQUEST_URL)
		.permitAll()
		.antMatchers(HttpMethod.POST , SecurityConstants.PASSWORD_RESET_URL)
		.permitAll()
		.antMatchers(HttpMethod.GET , SecurityConstants.EXPORT_PDF)
		.permitAll()
		.anyRequest().authenticated() //sign up authorized be9i lkol le
			//	.anyRequest().permitAll()
		//.and().oauth2Login()
		.and()
		.addFilter(getAuthenticationFilter())
		.addFilter(new AuthorizationFilter(authenticationManager())) //lkool lezem iconectiw ken sign up
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // bch nkoulou l spring security eli rest api te3na lezem ikounou stateless
//	}				// maghir matsir http session
		
//	http.cors();
	}
	@Override
	public void configure (AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder); //tkolou anehi interface taa service eli theb testaamlha o bch tnjm taaml authentication
	}
	
	public AuthenticationFilter getAuthenticationFilter() throws Exception {
		final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
		filter.setFilterProcessesUrl("/users/login");
		return filter;
	}  //bch tbadel url taa login
}