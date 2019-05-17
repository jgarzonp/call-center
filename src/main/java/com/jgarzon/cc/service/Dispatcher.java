package com.jgarzon.cc.service;

import com.jgarzon.cc.model.Call;
import com.jgarzon.cc.model.CallResult;
import com.jgarzon.cc.model.worker.Worker;
import com.jgarzon.cc.service.queue.DispatcherQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * Main Service class that defines the logic to take a call and assign an agent to receive the call.
 *
 * @author <a href="mailto:jgarzon@gptech.com.co">Jonathan Garz&oacute;n</a>
 * @version 1.0 - 2019-05-16 11:47 AM
 * @since call-center-1.0.0
 */
@Service
public class Dispatcher {

    // -----------------------------------------------------------------------------------------------------------------
    // Logger
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Logger instance
     */
    private static final Logger LOG = LoggerFactory.getLogger(Dispatcher.class);

    // -----------------------------------------------------------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * DisptcherQueue service instance
     */
    private final DispatcherQueue dispatcherQueue;

    // -----------------------------------------------------------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Service constructor with dependency injection.
     *
     * @param dispatcherQueue the {@link DispatcherQueue} instance to set
     */
    @Autowired
    public Dispatcher(DispatcherQueue dispatcherQueue) {
        this.dispatcherQueue = dispatcherQueue;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Methods Implementation -- It does not require Java Doc, because it is in the interface method definition
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Receives a call and assigns an available worker to attend the call, if there is no available workers, the service wait until there will be an
     * available one.
     *
     * @param call the call message to process
     * @return The result of the call processing
     */
    @Async
    public Future<CallResult> dispatchCall(Call call) {
        LOG.info("Dispatching Call: {}", call);
        CallResult callResult = new CallResult();
        callResult.setCall(call);
        long startMillis = System.currentTimeMillis();
        Worker worker;
        do {
            worker = dispatcherQueue.get();
            if (worker == null) {
                LOG.debug("No Available Workers yet, waiting for a free one to attend the call");
            }
        } while (worker == null);
        LOG.info("Worker {} assigned for call: {}", worker, call);
        callResult.setWaitingTime(System.currentTimeMillis() - startMillis);
        String callResultMessage = worker.processCall(call);
        callResult.setResultMessage(callResultMessage);
        callResult.setWorkerType(worker.getWorkerType());
        callResult.setTotalTime(System.currentTimeMillis() - startMillis);
        // Frees the worker and sets it as available again
        dispatcherQueue.add(worker);
        return new AsyncResult<>(callResult);
    }

}
