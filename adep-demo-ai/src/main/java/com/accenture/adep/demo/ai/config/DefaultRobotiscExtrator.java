package com.accenture.adep.demo.ai.config;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import com.accenture.adep.demo.ai.webworm.service.WebwormService;

public class DefaultRobotiscExtrator  implements ExtIfDataExtrator{
	
	@Autowired
	private WebwormService webwormService;
	
	public void extrator(LocalDateTime lastExecutedTime) {
	    System.out.println("$$$$$$$$$$$$$$DefaultRobotiscExtrator");
	    webwormService.saveRoboticsContent();
	}
}
