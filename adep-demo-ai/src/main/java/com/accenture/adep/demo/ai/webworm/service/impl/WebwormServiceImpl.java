package com.accenture.adep.demo.ai.webworm.service.impl;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.accenture.adep.demo.ai.common.ExtIfRunnerControlCommandRepository;
import com.accenture.adep.demo.ai.common.ExtIfRunnerStatusRepository;
import com.accenture.adep.demo.ai.common.RoboticsContentRepository;
import com.accenture.adep.demo.ai.config.ExtIfName;
import com.accenture.adep.demo.ai.config.ExtIfRunnerControlCommand;
import com.accenture.adep.demo.ai.config.ExtIfRunnerControlCommand.Command;
import com.accenture.adep.demo.ai.config.ExtIfRunnerStatus;
import com.accenture.adep.demo.ai.config.ExtIfRunnerStatus.Status;
import com.accenture.adep.demo.ai.config.RoboticsContent;
import com.accenture.adep.demo.ai.webworm.service.WebwormService;
import com.mongodb.util.JSON;

@Service
public class WebwormServiceImpl implements WebwormService {

    @Autowired  
    RestTemplate restTemplate; 
    
    @Autowired
    private ExtIfRunnerStatusRepository extIfRunnerStatusRepository;

    @Autowired
    private ExtIfRunnerControlCommandRepository extIfRunnerControllerRepository;
    
    @Autowired
    private RoboticsContentRepository roboticsContentRepository;
    
    private String weatherUrl = "http://samples.openweathermap.org/data/2.5/weather?";
    private String roboticsUrl = "http://10.0.0.4:8011/src_0206/src/05.request.jsp";
    
    @Override
    public void updateExtIfStatus(ExtIfName extIfName, Status status, String message) {
        // Get the existed Entity if it exists
        ExtIfRunnerStatus extIfRunnerStatus = extIfRunnerStatusRepository.findOne(extIfName.toString());
        if (extIfRunnerStatus == null) {
            extIfRunnerStatus = new ExtIfRunnerStatus();
        }
        extIfRunnerStatus.setExtIfName(extIfName.toString());
        extIfRunnerStatus.setStatus(status);
        extIfRunnerStatus.setMessage(message);

        // Update to Processing, it will clear the starton items
        if (Status.PROCESSING == status) {
            extIfRunnerStatus.setEndedOn(null);
            extIfRunnerStatus.setStartedOn(LocalDateTime.now());
        }

        if (Status.COMPLETED == status) {
            extIfRunnerStatus.setEndedOn(LocalDateTime.now());
            extIfRunnerStatus.setLastExecutedOn(extIfRunnerStatus.getStartedOn());

        }
        if (Status.ERROR == status) {
            extIfRunnerStatus.setEndedOn(LocalDateTime.now());
        }
        extIfRunnerStatusRepository.save(extIfRunnerStatus);
    }

    @Override
    public ExtIfRunnerStatus queryExtIfStatus(ExtIfName extIfName) {
        return extIfRunnerStatusRepository.findOne(extIfName.toString());
    }

    @Override
    public void issueControlComand(ExtIfName extIfName, Command command) {
    	ExtIfRunnerControlCommand controllerCommand = new ExtIfRunnerControlCommand();
		controllerCommand.setCommand(command);
		controllerCommand.setExtIfName(extIfName.toString());
		controllerCommand.setIssuedOn(LocalDateTime.now());
		extIfRunnerControllerRepository.save(controllerCommand);
        
    }

    @Override
    public ExtIfRunnerControlCommand getControlCommand(ExtIfName extIfName) {
        return extIfRunnerControllerRepository.findOne(extIfName.toString());
    }

	@Override
	public String getWeather(String lat, String lon) {
        // Set the http request body
		Map<String, String> requestParam = new LinkedHashMap<String, String>();
		requestParam.put("lat", lat);
		requestParam.put("lon", lon);
		requestParam.put("appid", "b1b15e88fa797225412429c1c50c122a1");
		

		for (String key : requestParam.keySet()) {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(this.weatherUrl);
			stringBuilder.append("&");
			stringBuilder.append(key);
			stringBuilder.append("=");
			stringBuilder.append("{");
			stringBuilder.append(key);
			stringBuilder.append("}");
			this.weatherUrl = stringBuilder.toString();
		}

		ResponseEntity<String> result = restTemplate.exchange(this.weatherUrl, HttpMethod.GET, null, String.class,
				requestParam);
		Object parseResult = JSON.parse(result.getBody());
        System.out.println("$$$$$$$ getWebworm $$$$$$$ " + parseResult);
        System.out.println("$$$$$$$ getWebworm $$$$$$$ " + parseResult.toString());
        return parseResult.toString();
	}
	
	@Override
    public String getRoboticsContent(String type) {
        return "";
    }

	@Override
	public void saveRoboticsContent() {
		ResponseEntity<String> result = restTemplate.exchange(this.roboticsUrl, HttpMethod.GET, null, String.class);
		Object parseResult = JSON.parse(result.getBody());
		RoboticsContent roboticsContent = new RoboticsContent();
		roboticsContent.setContent(parseResult.toString());
		//TODO roboticsContent.setType(type);
		roboticsContentRepository.save(roboticsContent);		
	}

}
