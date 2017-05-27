package com.accenture.adep.demo.ai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExfIfConfig {

    @Bean
    public DefaultRobotiscExtrator getDefaultRobotiscExtrator() {
        return new DefaultRobotiscExtrator();
    }
    
	@Bean
	public ExtIfRunner getExtIfRunner() {

		ExtIfRunner extIfRunner = new ExtIfRunner();
		extIfRunner.registerExtIf(ExtIfName.Robotis, this.getDefaultRobotiscExtrator());
		return extIfRunner;
	}

}