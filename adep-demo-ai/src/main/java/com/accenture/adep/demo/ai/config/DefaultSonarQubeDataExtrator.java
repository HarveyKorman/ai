package com.accenture.adep.demo.ai.config;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.accenture.adep.demo.ai.webworm.service.WebwormService;

public class DefaultSonarQubeDataExtrator  implements ExtIfDataExtrator{
	
	@Autowired
	private WebwormService webwormService;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public void extrator(LocalDateTime lastExecutedTime) {
		/*SonarQubeSetting sonarQubeSetting = extIfService.getExtIfSetting().getSonarQubeSetting();
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> result = restTemplate.exchange(sonarQubeSetting.getSonarqubeUrl(), HttpMethod.GET, null, String.class);
		Object parseResult = JSON.parse(result.getBody());
		if(!(parseResult instanceof DBObject)){
			throw new SystemException("cannot get the data by api");
		}
		DBObject dbObject = (DBObject)parseResult;
		DBObject componentObject = (DBObject)dbObject.get(FsaddConstant.SONARQUBE_ACCESS_KEY);
		componentObject.put(FsaddConstant.INSERT_DATA_COL, FsaddUtil.convertLocaldateTimeToString(LocalDateTime.now(),FsaddConstant.DATAE_FORMAT_YYYYMMDDHHMMSS));
		mongoTemplate.insert(componentObject, sonarQubeSetting.getIssuesCollectionName());*/
	}
}
