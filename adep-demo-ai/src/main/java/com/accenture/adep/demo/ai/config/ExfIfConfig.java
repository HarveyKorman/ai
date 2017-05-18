package com.accenture.adep.demo.ai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExfIfConfig {

    @Bean
    public DefaultSonarQubeDataExtrator getDefaultSonarQubeDataExtrator() {
        return new DefaultSonarQubeDataExtrator();
    }
    
	@Bean
	public ExtIfRunner getExtIfRunner() {

		ExtIfRunner extIfRunner = new ExtIfRunner();
		extIfRunner.registerExtIf(ExtIfName.Redmine, this.getDefaultSonarQubeDataExtrator());
		return extIfRunner;
	}

}