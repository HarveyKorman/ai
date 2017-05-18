package com.accenture.adep.demo.ai.webworm.service;


import com.accenture.adep.demo.ai.config.ExtIfName;
import com.accenture.adep.demo.ai.config.ExtIfRunnerControlCommand;
import com.accenture.adep.demo.ai.config.ExtIfRunnerControlCommand.Command;
import com.accenture.adep.demo.ai.config.ExtIfRunnerStatus;
import com.accenture.adep.demo.ai.config.ExtIfRunnerStatus.Status;

public interface WebwormService {
    void getWebworm();
    /**
     * Update external-if executed status
     * 
     * @param extIfName
     *            External-if Name
     * @param status
     *            Status
     * @param message
     *            message
     */
    void updateExtIfStatus(ExtIfName extIfName, Status status, String message);

    /**
     * Query external-if status
     * 
     * @param extIfName
     *            External-If name
     * @return external-if status
     */
    ExtIfRunnerStatus queryExtIfStatus(ExtIfName extIfName);

    /**
     * Issue control command
     * 
     * @param extIfName
     *            External-If name
     * @param command
     *            control command
     */
    void issueControlComand(ExtIfName extIfName, Command command);

    /**
     * Query control command
     * 
     * @param extIfName
     *            External-If name
     * @return Control Command
     */
    ExtIfRunnerControlCommand getControlCommand(ExtIfName extIfName);
}
