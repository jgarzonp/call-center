package com.jgarzon.cc.web;

import com.jgarzon.cc.model.Call;
import com.jgarzon.cc.model.CallResult;
import com.jgarzon.cc.service.Dispatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * REST End-Points definition for <code>/api/dispatcher</code> resource
 *
 * @author <a href="mailto:jgarzon@gptech.com.co">Jonathan Garz&oacute;n</a>
 * @version 1.0 - 2019-05-16 12:07 PM
 * @since call-center-1.0.0
 */
@RestController
@RequestMapping("/api/dispatcher")
public class DispatcherResource {

    // -----------------------------------------------------------------------------------------------------------------
    // Logger
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Logger instance
     */
    private static final Logger LOG = LoggerFactory.getLogger(DispatcherResource.class);

    // -----------------------------------------------------------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Dispatcher service instance
     */
    private final Dispatcher dispatcher;

    // -----------------------------------------------------------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Constructor for Dependency Injection
     *
     * @param dispatcher the dispatcher service instance to inject
     */
    @Autowired
    public DispatcherResource(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // EndPoints Methods Definition
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * POST /api/dispatcher : Receives a call and sent it to be attended by an agent
     *
     * @param call the message call to process
     * @return The {@link ResponseEntity} with status 201 (Created) and the body with the call result if the operation is ok.
     */
    @PostMapping
    public ResponseEntity<CallResult> dispatchCall(@NotNull @RequestBody Call call) {
        LOG.info("Dispatching Call: Message={}", call);
        HttpStatus httpStatus = HttpStatus.CREATED;
        CallResult callResult = null;
        try {
            Future<CallResult> futureResult = dispatcher.dispatchCall(call);
            while (true) {
                if (futureResult.isDone()) {
                    callResult = futureResult.get();
                    break;
                } else if (futureResult.isCancelled()) {
                    httpStatus = HttpStatus.CONFLICT;
                    LOG.error("Thread error, cancelling request");
                    break;
                }
            }
        } catch (ExecutionException | InterruptedException e) {
            LOG.error("Error Processing Call", e);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return ResponseEntity.status(httpStatus).body(callResult);
    }

}
