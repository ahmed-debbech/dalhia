package tn.dalhia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import tn.dalhia.security.AppProperties;
import tn.dalhia.security.AppProperties;

@EnableScheduling
@EnableAspectJAutoProxy
@EnableWebMvc
@SpringBootApplication
public class DalhiaApplication {
	
	

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/email-verification").setViewName("email-verification");
	}

	public static void main(String[] args) {
		SpringApplication.run(DalhiaApplication.class, args);
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder () {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SpringApplicationContext springApplicationContext () {
		return new SpringApplicationContext();
	}
	@Bean(name="AppProperties")
	public AppProperties getAppProperties () {
		return new AppProperties();
	}
	
}
