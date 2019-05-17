package com.jgarzon.cc.model.worker;

import com.jgarzon.cc.model.Call;
import com.jgarzon.cc.model.enumeration.WorkerType;

/**
 * Defines the Worker common structure ant its operations.
 * <p>
 * It extends the {@link Comparable} interface to manage the {@link java.util.concurrent.PriorityBlockingQueue} instance to manage the type of workers
 * by priority.
 * </p>
 *
 * @author <a href="mailto:jgarzon@gptech.com.co">Jonathan Garz&oacute;n</a>
 * @version 1.0 - 2019-05-16 10:27 AM
 * @since call-center-1.0.0
 */
public interface Worker<E> extends Comparable<E> {

    /**
     * Processes the call
     *
     * @param call the call information to process
     * @return The result of the call
     */
    String processCall(Call call);

    /**
     * Gets the worker type description that processed the current call
     *
     * @return The worker type description
     */
    WorkerType getWorkerType();

}
