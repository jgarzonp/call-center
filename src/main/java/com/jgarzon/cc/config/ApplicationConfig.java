package com.jgarzon.cc.config;

import com.jgarzon.cc.model.worker.Worker;
import com.jgarzon.cc.model.worker.impl.ManagerWorker;
import com.jgarzon.cc.model.worker.impl.OperatorWorker;
import com.jgarzon.cc.model.worker.impl.SupervisorWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * The Application configuration definition class
 *
 * @author <a href="mailto:jgarzon@gptech.com.co">Jonathan Garz&oacute;n</a>
 * @version 1.0 - 2019-05-16 11:31 AM
 * @since call-center-1.0.0
 */
@EnableAsync
@Configuration
public class ApplicationConfig {

    // -----------------------------------------------------------------------------------------------------------------
    // Logger
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Logger instance
     */
    private static final Logger LOG = LoggerFactory.getLogger(ApplicationConfig.class);

    // -----------------------------------------------------------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Application properties
     */
    private final ApplicationProperties properties;

    // -----------------------------------------------------------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Configuration constructor
     *
     * @param properties The application properties to set
     */
    @Autowired
    public ApplicationConfig(ApplicationProperties properties) {
        this.properties = properties;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Beans definitions
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Builds the available workers queue Spring Java Bean, it depends of the application properties defined in the <code>application.yml</code>
     * file.
     *
     * @return A {@link Queue} object with the {@link Worker} objects.
     */
    @Bean
    public Queue<Worker> availableWorkersBean() {
        LOG.info("Building Available Workers Queue by configuration");
        Queue<Worker> workers = new PriorityBlockingQueue<>();
        for (int i = 0; i < properties.getOperatorsSize(); i++) {
            OperatorWorker operatorWorker = new OperatorWorker();
            operatorWorker.setId(i);
            workers.add(operatorWorker);
        }
        for (int i = 0; i < properties.getSupervisorsSize(); i++) {
            SupervisorWorker supervisorWorker = new SupervisorWorker();
            supervisorWorker.setId(i);
            workers.add(supervisorWorker);
        }
        for (int i = 0; i < properties.getManagersSize(); i++) {
            ManagerWorker managerWorker = new ManagerWorker();
            managerWorker.setId(i);
            workers.add(managerWorker);
        }
        return workers;
    }

}
