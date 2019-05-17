package com.jgarzon.cc.service.queue;

import com.jgarzon.cc.model.worker.Worker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Queue;

/**
 * Dispatcher Queue Services implementation that contains the Workers queue who will receive the calls
 *
 * @author <a href="mailto:jgarzon@gptech.com.co">Jonathan Garz&oacute;n</a>
 * @version 1.0 - 2019-05-16 10:22 AM
 * @since call-center-1.0.0
 */
@Service
public final class DispatcherQueue {

    // -----------------------------------------------------------------------------------------------------------------
    // Logger
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Logger instance
     */
    private static final Logger LOG = LoggerFactory.getLogger(DispatcherQueue.class);

    // -----------------------------------------------------------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The Worker Queue to define the agents to receive the calls
     */
    private final Queue<Worker> workersQueue;

    // -----------------------------------------------------------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Service class constructor to initialize the workers queue with the parameterized Spring bean.
     *
     * @param workersQueue the workersQueue value to set
     */
    @Autowired
    public DispatcherQueue(@NotNull Queue<Worker> workersQueue) {
        this.workersQueue = workersQueue;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Logical Method Implementations
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Adds a worker to the queue of available workers
     *
     * @param worker the {@link Worker} object to add
     */
    public void add(Worker worker) {
        LOG.info("Adding worker to queue: {}", worker);
        workersQueue.add(worker);
    }

    /**
     * Gets a worker from the queue of available workers, returns <code>null</code> if ther is no available workers to receive the call
     *
     * @return the {@link Worker} object from the queue
     */
    public Worker get() {
        LOG.debug("Getting worker from queue");
        return workersQueue.poll();
    }
}
