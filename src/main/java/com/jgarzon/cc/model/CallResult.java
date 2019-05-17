package com.jgarzon.cc.model;

import com.jgarzon.cc.model.enumeration.WorkerType;

import java.io.Serializable;

/**
 * POJO that represent a Call Result to return after call processing
 *
 * @author <a href="mailto:jgarzon@gptech.com.co">Jonathan Garz&oacute;n</a>
 * @version 1.0 - 2019-05-16 12:16 PM
 * @since call-center-1.0.0
 */
public class CallResult implements Serializable {

    // -----------------------------------------------------------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The original call
     */
    private Call call;

    /**
     * The result call message
     */
    private String resultMessage;

    /**
     * The total time of call
     */
    private Long totalTime;

    /**
     * The waiting time to attend the call
     */
    private Long waitingTime;

    /**
     * The worker type who attended the call
     */
    private WorkerType workerType;

    // -----------------------------------------------------------------------------------------------------------------
    // Getters & Setters
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Gets the originalMessage value
     *
     * @return the originalMessage value
     */
    public Call getCall() {
        return call;
    }

    /**
     * Sets the originalMessage value
     *
     * @param call the call to set
     */
    public void setCall(Call call) {
        this.call = call;
    }

    /**
     * Gets the resultMessage value
     *
     * @return the resultMessage value
     */
    public String getResultMessage() {
        return resultMessage;
    }

    /**
     * Sets the resultMessage value
     *
     * @param resultMessage the resultMessage to set
     */
    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    /**
     * Gets the totalTime value
     *
     * @return the totalTime value
     */
    public Long getTotalTime() {
        return totalTime;
    }

    /**
     * Sets the totalTime value
     *
     * @param totalTime the totalTime to set
     */
    public void setTotalTime(Long totalTime) {
        this.totalTime = totalTime;
    }

    /**
     * Gets the waitingTime value
     *
     * @return the waitingTime value
     */
    public Long getWaitingTime() {
        return waitingTime;
    }

    /**
     * Sets the waitingTime value
     *
     * @param waitingTime the waitingTime to set
     */
    public void setWaitingTime(Long waitingTime) {
        this.waitingTime = waitingTime;
    }

    /**
     * Gets the workerType value
     *
     * @return the workerType value
     */
    public WorkerType getWorkerType() {
        return workerType;
    }

    /**
     * Sets the workerType value
     *
     * @param workerType the workerType to set
     */
    public void setWorkerType(WorkerType workerType) {
        this.workerType = workerType;
    }

    @Override
    public String toString() {
        return "CallResult{" + "call='" + call + '\'' + ", resultMessage='" + resultMessage + '\'' + ", totalTime=" + totalTime + ", waitingTime=" +
                waitingTime + ", workerType=" + workerType + '}';
    }
}
