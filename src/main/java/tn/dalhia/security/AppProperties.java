package tn.dalhia.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component//bch njmou autowirdiw objects nahtajeohom bch nakraw mel property file
public class AppProperties { //nakra hajet ml property file

	@Autowired
	private Environment env;
	
	public String getTokenSecret() {
		return env.getProperty("tokenSecret");
	}
}


