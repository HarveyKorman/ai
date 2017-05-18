package com.accenture.adep.demo.ai.webworm.service.impl;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.accenture.adep.demo.ai.config.ExtIfName;
import com.accenture.adep.demo.ai.config.ExtIfRunnerControlCommand;
import com.accenture.adep.demo.ai.config.ExtIfRunnerControlCommand.Command;
import com.accenture.adep.demo.ai.config.ExtIfRunnerStatus;
import com.accenture.adep.demo.ai.config.ExtIfRunnerStatus.Status;
import com.accenture.adep.demo.ai.webworm.service.WebwormService;

@Service
public class WebwormServiceImpl implements WebwormService {

    @Autowired  
    RestTemplate restTemplate; 
    
    @Override
    public void getWebworm() {
        String url = "http://samples.openweathermap.org/data/2.5/weather?lat=35&lon=139&appid=b1b15e88fa797225412429c1c50c122a1";
        JSONObject json = restTemplate.getForEntity(url, JSONObject.class).getBody();
        System.out.println("$$$$$$$ getWebworm $$$$$$$ " + json);
        System.out.println("$$$$$$$ getWebworm $$$$$$$ " + json.toString());
    }

    @Override
    public void updateExtIfStatus(ExtIfName extIfName, Status status, String message) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public ExtIfRunnerStatus queryExtIfStatus(ExtIfName extIfName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void issueControlComand(ExtIfName extIfName, Command command) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public ExtIfRunnerControlCommand getControlCommand(ExtIfName extIfName) {
        // TODO Auto-generated method stub
        return null;
    }

}
