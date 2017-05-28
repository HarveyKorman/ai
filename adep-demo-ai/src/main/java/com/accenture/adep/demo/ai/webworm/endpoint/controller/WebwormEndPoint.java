package com.accenture.adep.demo.ai.webworm.endpoint.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.adep.demo.ai.webworm.service.WebwormService;

@RestController
public class WebwormEndPoint {
	@Autowired
    WebwormService webwormService;
    
	@RequestMapping("/getWeather")
	public String getWeather(){
		String lat = "35";
		String lon = "139";
	    return webwormService.getWeather(lat, lon);
	}
	
	@RequestMapping("/getRoboticsContent")
	public String getRoboticsContent(){
		//TODO type
	    return webwormService.getRoboticsContent("");
	}
}
