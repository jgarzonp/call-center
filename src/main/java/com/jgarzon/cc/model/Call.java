package com.jgarzon.cc.model;

import java.util.Objects;

/**
 * POJO that represents the call to be managed by the workers
 *
 * @author <a href="mailto:jgarzon@gptech.com.co">Jonathan Garz&oacute;n</a>
 * @version 1.0 - 2019-05-16 12:16 PM
 * @since call-center-1.0.0
 */
public class Call {

    // -----------------------------------------------------------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The call ID
     */
    private Long id;

    // -----------------------------------------------------------------------------------------------------------------
    // Getters & Setters
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Gets the ID value
     *
     * @return the ID value
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID value
     *
     * @param id the ID value to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Sets the ID value
     *
     * @param id the ID value to set
     */
    public Call id(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (!(o instanceof Call)) { return false; }
        Call call = (Call) o;
        return getId().equals(call.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Call{" + "id=" + id + '}';
    }
}
