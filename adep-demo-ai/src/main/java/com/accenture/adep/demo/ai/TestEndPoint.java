package com.accenture.adep.demo.ai;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestEndPoint {
	
	@RequestMapping("/webworm/")
	public String test(){
		return "This is AI test endpoint�I ";
	}
	
	

}
