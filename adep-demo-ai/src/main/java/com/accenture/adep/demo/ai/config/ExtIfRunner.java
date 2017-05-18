package com.accenture.adep.demo.ai.config;


import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import com.accenture.adep.demo.ai.config.ExtIfRunnerControlCommand.Command;
import com.accenture.adep.demo.ai.config.ExtIfRunnerStatus.Status;
import com.accenture.adep.demo.ai.webworm.service.WebwormService;


public class ExtIfRunner implements Runnable {

	private static Logger log = LoggerFactory.getLogger(ExtIfRunner.class);

	@Autowired
	private WebwormService webwormService;
	
	private ThreadPoolTaskScheduler scheduler;

    private Map<ExtIfName, ExtIfDataExtrator> extIfExtratorMap = new LinkedHashMap<>();
	
	@PostConstruct
	public void start() {
		// Open the external data extrator and mapper
		extIfExtratorMap.forEach((extIfName, extIfDataExtrator) -> {
		    webwormService.issueControlComand(extIfName, Command.OPEN);
		    webwormService.updateExtIfStatus(extIfName, Status.STOPPED, "restart");
		});
		
		scheduler = new ThreadPoolTaskScheduler();
		scheduler.initialize();
		CronTrigger ct = new CronTrigger("*/5 * * * * ?");
		scheduler.schedule(this, ct);
	}

	@PreDestroy
	public void stop() {
		// Close the external data extrator and mapper
		extIfExtratorMap.forEach((extIfName, extIfDataExtrator) -> {
		    webwormService.issueControlComand(extIfName, Command.STOP);
		});
		scheduler.shutdown();
	}
	
	public void registerExtIf(ExtIfName extIfName, ExtIfDataExtrator extrator) {
        extIfExtratorMap.put(extIfName, extrator);
    }

	public void run() {
		log.info("ExtIf Runner Start");
		// Execute the extrator and mapper
		extIfExtratorMap.forEach((extIfName, extIfDataExtrator) -> {
			try {
				ExtIfRunnerStatus status = webwormService.queryExtIfStatus(extIfName);
				ExtIfRunnerControlCommand command = webwormService.getControlCommand(extIfName);
				// When error is occcurred in last execution or there are stop
				// command received data exetrator or mapper will not be
				// executed
				if (status.getStatus() != Status.ERROR && command.getCommand() != Command.STOP) {
					log.info("{} external if started.lastExecuted={}", extIfName, status.getStartedOn());
					// Updating updateExtIfStatus
					webwormService.updateExtIfStatus(extIfName, Status.PROCESSING, "Start from ExtIfRunner");
					if (extIfDataExtrator != null) {
						extIfDataExtrator.extrator(status.getLastExecutedOn());
					}
					webwormService.updateExtIfStatus(extIfName, Status.COMPLETED, "ended from ExtIfRunner");
					log.info("{} external if successed.", extIfName);
				}

			} catch (Exception e) {
			    webwormService.updateExtIfStatus(extIfName, Status.ERROR, "ended from ExtIfRunner");
				log.error("ExtIf Runner error.", e);
			}
		});
		log.info("ExtIf Runner End");
	}
}
