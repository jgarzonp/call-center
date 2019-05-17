package com.jgarzon.cc.model.worker.impl;

import com.jgarzon.cc.model.enumeration.WorkerType;
import com.jgarzon.cc.model.worker.AbstractWorker;
import com.jgarzon.cc.model.worker.Worker;

/**
 * {@link Worker} implementation for Manager agent type
 *
 * @author <a href="mailto:jgarzon@gptech.com.co">Jonathan Garz&oacute;n</a>
 * @version 1.0 - 2019-05-16 11:22 AM
 * @since call-center-1.0.0
 */
public class ManagerWorker extends AbstractWorker {

    // -----------------------------------------------------------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Non-args constructor
     */
    public ManagerWorker() {
        super(3);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Method Implementation -- It does not require Java Doc, because it is in the interface method definition
    // -----------------------------------------------------------------------------------------------------------------

    @Override
    public WorkerType getWorkerType() {
        return WorkerType.MANAGER;
    }

}
