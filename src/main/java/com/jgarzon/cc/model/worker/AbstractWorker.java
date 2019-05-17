package com.jgarzon.cc.model.worker;

import com.jgarzon.cc.model.Call;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.Random;

/**
 * Abstract Worker definition for common logic to process a call
 *
 * @author <a href="mailto:jgarzon@gptech.com.co">Jonathan Garz&oacute;n</a>
 * @version 1.0 - 2019-05-16 10:46 AM
 * @see Worker
 * @since call-center-1.0.0
 */
public abstract class AbstractWorker implements Worker<AbstractWorker> {

    // -----------------------------------------------------------------------------------------------------------------
    // Logger
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Logger instance
     */
    private static final Logger LOG = LoggerFactory.getLogger(AbstractWorker.class);

    // -----------------------------------------------------------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Constant to define the complete message of call if it failed
     */
    private static final String CALL_FAILED = "Call {0} failed by Agent {1}";

    /**
     * Constant to define the complete message of call processing result
     */
    private static final String CALL_RESULT = "Call {0} attended for {1} seconds by Agent {2}";

    // -----------------------------------------------------------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------------------------------------------------------
    /**
     * The Priority ID
     */
    private final Integer priority;
    /**
     * The Worker ID
     */
    private Integer id;

    // -----------------------------------------------------------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Constructor with the priority definition ID
     *
     * @param priority the priority to set
     */
    protected AbstractWorker(Integer priority) {
        this.priority = priority;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Getters & Setters
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Gets the worker ID
     *
     * @return the worker ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the Worker ID
     *
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the priority
     *
     * @return the worker priority
     */
    public Integer getPriority() {
        return priority;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Method Implementation -- It does not require Java Doc, because it is in the interface method definition
    // -----------------------------------------------------------------------------------------------------------------

    @Override
    public String processCall(Call call) {
        String callResult;
        Random r = new Random();
        try {
            int seconds = r.ints(5, 10 + 1).limit(1).findFirst().orElse(NumberUtils.INTEGER_ZERO);
            Thread.sleep(seconds * 1000);
            callResult = MessageFormat.format(CALL_RESULT, call.getId(), seconds, this.toString());
        } catch (InterruptedException e) {
            LOG.error("Error processing call: {}", call);
            callResult = MessageFormat.format(CALL_FAILED, call.getId(), this.toString());
        }
        return callResult;
    }

    @Override
    public int compareTo(AbstractWorker obj) {
        int result;
        if (obj == null || obj.priority == null) {
            result = -1;
        } else if (priority == null) {
            result = 1;
        } else {
            result = priority.compareTo(obj.priority);
        }
        return result;
    }

    @Override
    public String toString() {
        return "Worker(" + id + ":[" + getWorkerType() + "])";
    }
}
