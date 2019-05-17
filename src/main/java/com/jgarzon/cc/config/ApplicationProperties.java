package com.jgarzon.cc.config;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to the application.
 * <p>
 * Properties are configured in the application.yml file.
 * </p>
 *
 * @author <a href="mailto:jgarzon@gptech.com.co">Jonathan Garz&oacute;n</a>
 * @version 1.0 - 2019-05-16 9:46 AM
 * @since call-center-1.0.0
 */
@ConfigurationProperties(prefix = "application")
public class ApplicationProperties {

    // -----------------------------------------------------------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Asynchronous configuration
     */
    private final Async async = new Async();
    /**
     * The Operators Size property, default value 7
     */
    private Integer operatorsSize = 7;
    /**
     * The Supervisors Size property, default value 2
     */
    private Integer supervisorsSize = 2;
    /**
     * The Managers Size property, default value 1
     */
    private Integer managersSize = NumberUtils.INTEGER_ONE;

    // -----------------------------------------------------------------------------------------------------------------
    // Getters & Setters
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Gets the operatorsSize value
     *
     * @return the operatorsSize value
     */
    public Integer getOperatorsSize() {
        return operatorsSize;
    }

    /**
     * Sets the operatorsSize value
     *
     * @param operatorsSize the operatorsSize value to set
     */
    public void setOperatorsSize(Integer operatorsSize) {
        this.operatorsSize = operatorsSize;
    }

    /**
     * Gets the supervisorsSize value
     *
     * @return the supervisorsSize value
     */
    public Integer getSupervisorsSize() {
        return supervisorsSize;
    }

    /**
     * Sets the supervisorsSize value
     *
     * @param supervisorsSize the supervisorsSize value to set
     */
    public void setSupervisorsSize(Integer supervisorsSize) {
        this.supervisorsSize = supervisorsSize;
    }

    /**
     * Gets the managersSize value
     *
     * @return the managersSize value
     */
    public Integer getManagersSize() {
        return managersSize;
    }

    /**
     * Sets the managersSize value
     *
     * @param managersSize the managersSize value to set
     */
    public void setManagersSize(Integer managersSize) {
        this.managersSize = managersSize;
    }

    /**
     * Gets the async value
     *
     * @return the async value
     */
    public Async getAsync() {
        return async;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Subclasses Definition
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Asynchronous configuration properties
     */
    public static class Async {

        /**
         * Asynchronous core pool size, default 1
         */
        private int corePoolSize = 1;

        /**
         * Asynchronous max pool size, default 10
         */
        private int maxPoolSize = 10;

        /**
         * Asynchronous queue capacity, default 100
         */
        private int queueCapacity = 100;

        /**
         * Asynchronous thread name prefix
         */
        private String threadNamePrefix = "CC-";

        /**
         * Gets the corePoolSize value
         *
         * @return the corePoolSize
         */
        public int getCorePoolSize() {
            return corePoolSize;
        }

        /**
         * Sets the corePoolSize value
         *
         * @param corePoolSize the corePoolSize to set
         */
        public void setCorePoolSize(int corePoolSize) {
            this.corePoolSize = corePoolSize;
        }

        /**
         * Gets the maxPoolSize value
         *
         * @return the maxPoolSize
         */
        public int getMaxPoolSize() {
            return maxPoolSize;
        }

        /**
         * Sets the maxPoolSize value
         *
         * @param maxPoolSize the maxPoolSize to set
         */
        public void setMaxPoolSize(int maxPoolSize) {
            this.maxPoolSize = maxPoolSize;
        }

        /**
         * Gets the queueCapacity value
         *
         * @return the queueCapacity
         */
        public int getQueueCapacity() {
            return queueCapacity;
        }

        /**
         * Sets the queueCapacity value
         *
         * @param queueCapacity the queueCapacity to set
         */
        public void setQueueCapacity(int queueCapacity) {
            this.queueCapacity = queueCapacity;
        }

        /**
         * Gets the threadNamePrefix value
         *
         * @return the threadNamePrefix
         */
        public String getThreadNamePrefix() {
            return threadNamePrefix;
        }

        /**
         * Sets the threadNamePrefix value
         *
         * @param threadNamePrefix the threadNamePrefix to set
         */
        public void setThreadNamePrefix(String threadNamePrefix) {
            this.threadNamePrefix = threadNamePrefix;
        }
    }

}
