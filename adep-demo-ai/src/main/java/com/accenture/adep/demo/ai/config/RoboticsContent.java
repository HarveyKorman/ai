package com.accenture.adep.demo.ai.config;

import java.time.LocalDateTime;

public class RoboticsContent {
	
	private LocalDateTime currentTime = LocalDateTime.now();
	private String content;
	private String type;
	public LocalDateTime getCurrentTime() {
		return currentTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
