package tn.dalhia;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class WebConfiguration implements WebMvcConfigurer{

//	@Override
//	public void addCorsMappings(CorsRegistry corsRegistry) {
//		corsRegistry.addMapping("/**").allowedOrigins("http://localhost:4200").allowedMethods("GET","POST","PUT","DELETE");
//	}
	
    
	   
	    protected void configure(HttpSecurity http) throws Exception {
	        http.cors();
	    }

	    @Bean
	    CorsConfigurationSource corsConfigurationSource() {
	    	final CorsConfiguration configuration = new CorsConfiguration();
	        UrlBasedCorsConfigurationSource source = new
	                UrlBasedCorsConfigurationSource();
	        
	  
	        configuration.applyPermitDefaultValues();
	        configuration.addAllowedHeader("Authorization");
	        configuration.addExposedHeader("Authorization");
	        configuration.addAllowedHeader("UserId");
	        configuration.addExposedHeader("UserId");
	        configuration.addAllowedHeader("Role");
	        configuration.addExposedHeader("Role");
	        configuration.addAllowedOrigin("*");
	        configuration.addAllowedHeader("Content-Type");
	        configuration.addAllowedHeader("x-xsrf-token");
	        configuration.addAllowedHeader("Authorization");
	        configuration.addAllowedHeader("Access-Control-Allow-Headers");
	        configuration.addAllowedHeader("Origin");
	        configuration.addAllowedHeader("Accept");
	        configuration.addAllowedHeader("X-Requested-With");
	        configuration.addAllowedHeader("Access-Control-Request-Method");
	        configuration.addAllowedHeader("Access-Control-Request-Headers");
	        configuration.addAllowedMethod("OPTIONS");
	        configuration.addAllowedMethod("GET");
	        configuration.addAllowedMethod("PUT");
	        configuration.addAllowedMethod("POST");
	        configuration.addAllowedMethod("DELETE");
	        source.registerCorsConfiguration("/**", configuration);
	        
	        return source;
	    }
}
