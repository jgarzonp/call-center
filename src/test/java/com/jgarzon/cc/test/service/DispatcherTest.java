package com.jgarzon.cc.test.service;

import com.jgarzon.cc.InitApp;
import com.jgarzon.cc.model.Call;
import com.jgarzon.cc.model.CallResult;
import com.jgarzon.cc.model.enumeration.WorkerType;
import com.jgarzon.cc.service.Dispatcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.junit.Assert.*;

/**
 * Dispatcher Service Tests
 *
 * @author <a href="mailto:jgarzon@gptech.com.co">Jonathan Garz&oacute;n</a>
 * @version 1.0 - 2019-05-16 8:08 PM
 * @since call-center-1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InitApp.class)
public class DispatcherTest {

    /**
     * Logger Instance
     */
    private static final Logger LOG = LoggerFactory.getLogger(DispatcherTest.class);

    /**
     * Subject Test
     */
    @Autowired
    private Dispatcher dispatcher;

    /**
     * Test to validate the call assignation depending by the agent role, the first 7 calls should be attended by operator type agents, the next 2
     * calls should be attended by supervisor agent rol, and the last one should be attended by a manager agent role.
     */
    @Test
    public void dispatchCall_ConcurrentCalls_ShouldSuccess() throws InterruptedException {

        Call callMessage0 = new Call().id(1L);
        Call callMessage1 = new Call().id(2L);
        Call callMessage2 = new Call().id(3L);
        Call callMessage3 = new Call().id(4L);
        Call callMessage4 = new Call().id(5L);
        Call callMessage5 = new Call().id(6L);
        Call callMessage6 = new Call().id(7L);
        Call callMessage7 = new Call().id(8L);
        Call callMessage8 = new Call().id(9L);
        Call callMessage9 = new Call().id(10L);

        Map<Call, WorkerType> callWorkerTypes = new LinkedHashMap<>();
        callWorkerTypes.put(callMessage0, WorkerType.OPERATOR);
        callWorkerTypes.put(callMessage1, WorkerType.OPERATOR);
        callWorkerTypes.put(callMessage2, WorkerType.OPERATOR);
        callWorkerTypes.put(callMessage3, WorkerType.OPERATOR);
        callWorkerTypes.put(callMessage4, WorkerType.OPERATOR);
        callWorkerTypes.put(callMessage5, WorkerType.OPERATOR);
        callWorkerTypes.put(callMessage6, WorkerType.OPERATOR);
        callWorkerTypes.put(callMessage7, WorkerType.SUPERVISOR);
        callWorkerTypes.put(callMessage8, WorkerType.SUPERVISOR);
        callWorkerTypes.put(callMessage9, WorkerType.MANAGER);

        List<Future<CallResult>> callResults = new ArrayList<>();
        synchronized (this) {
            callResults.add(dispatcher.dispatchCall(callMessage0));
            callResults.add(dispatcher.dispatchCall(callMessage1));
            callResults.add(dispatcher.dispatchCall(callMessage2));
            callResults.add(dispatcher.dispatchCall(callMessage3));
            callResults.add(dispatcher.dispatchCall(callMessage4));
            callResults.add(dispatcher.dispatchCall(callMessage5));
            callResults.add(dispatcher.dispatchCall(callMessage6));
            Thread.sleep(100);
            callResults.add(dispatcher.dispatchCall(callMessage7));
            callResults.add(dispatcher.dispatchCall(callMessage8));
            Thread.sleep(100);
            callResults.add(dispatcher.dispatchCall(callMessage9));
        }

        assertFalse(callResults.isEmpty());
        callResults.forEach((callResultFuture) -> {
            assertNotNull(callResultFuture);
            try {
                while (true) {
                    if (callResultFuture.isDone()) {
                        break;
                    }
                }
                CallResult callResult = callResultFuture.get();
                LOG.info("Result: {}", callResult);
                assertNotNull(callResult);
                assertNotNull(callResult.getCall());
                assertNotNull(callResult.getWaitingTime());
                assertEquals(0, callResult.getWaitingTime(), 1);
                assertEquals(callWorkerTypes.get(callResult.getCall()), callResult.getWorkerType());
            } catch (InterruptedException | ExecutionException e) {
                fail(e.getMessage());
            }
        });
    }

    /**
     * Test to validate what happens with a call when all the agents are busy, the logic was defined to set the call into a wait
     */
    @Test
    public void dispatchCall_ConcurrentCallsWithWaitingCall_ShouldSuccess() throws InterruptedException {

        Call callMessage0 = new Call().id(1L);
        Call callMessage1 = new Call().id(2L);
        Call callMessage2 = new Call().id(3L);
        Call callMessage3 = new Call().id(4L);
        Call callMessage4 = new Call().id(5L);
        Call callMessage5 = new Call().id(6L);
        Call callMessage6 = new Call().id(7L);
        Call callMessage7 = new Call().id(8L);
        Call callMessage8 = new Call().id(9L);
        Call callMessage9 = new Call().id(10L);
        Call callMessage10 = new Call().id(11L);

        Map<Call, WorkerType> callWorkerTypes = new LinkedHashMap<>();
        callWorkerTypes.put(callMessage0, WorkerType.OPERATOR);
        callWorkerTypes.put(callMessage1, WorkerType.OPERATOR);
        callWorkerTypes.put(callMessage2, WorkerType.OPERATOR);
        callWorkerTypes.put(callMessage3, WorkerType.OPERATOR);
        callWorkerTypes.put(callMessage4, WorkerType.OPERATOR);
        callWorkerTypes.put(callMessage5, WorkerType.OPERATOR);
        callWorkerTypes.put(callMessage6, WorkerType.OPERATOR);
        callWorkerTypes.put(callMessage7, WorkerType.SUPERVISOR);
        callWorkerTypes.put(callMessage8, WorkerType.SUPERVISOR);
        callWorkerTypes.put(callMessage9, WorkerType.MANAGER);

        Future<CallResult> callResultFuture10;
        List<Future<CallResult>> callResults = new ArrayList<>();
        synchronized (this) {
            callResults.add(dispatcher.dispatchCall(callMessage0));
            callResults.add(dispatcher.dispatchCall(callMessage1));
            callResults.add(dispatcher.dispatchCall(callMessage2));
            callResults.add(dispatcher.dispatchCall(callMessage3));
            callResults.add(dispatcher.dispatchCall(callMessage4));
            callResults.add(dispatcher.dispatchCall(callMessage5));
            callResults.add(dispatcher.dispatchCall(callMessage6));
            Thread.sleep(100);
            callResults.add(dispatcher.dispatchCall(callMessage7));
            callResults.add(dispatcher.dispatchCall(callMessage8));
            Thread.sleep(100);
            callResults.add(dispatcher.dispatchCall(callMessage9));
            Thread.sleep(100);
            callResultFuture10 = dispatcher.dispatchCall(callMessage10);
        }

        assertFalse(callResults.isEmpty());
        callResults.forEach((callResultFuture) -> {
            assertNotNull(callResultFuture);
            try {
                while (true) {
                    if (callResultFuture.isDone()) {
                        break;
                    }
                }
                CallResult callResult = callResultFuture.get();
                LOG.info("Result: {}", callResult);
                assertNotNull(callResult);
                assertNotNull(callResult.getCall());
                assertNotNull(callResult.getWaitingTime());
                assertEquals(0, callResult.getWaitingTime(), 10);
                assertEquals(callWorkerTypes.get(callResult.getCall()), callResult.getWorkerType());
            } catch (InterruptedException | ExecutionException e) {
                fail(e.getMessage());
            }
        });

        assertNotNull(callResultFuture10);
        try {
            while (true) {
                if (callResultFuture10.isDone()) {
                    break;
                }
            }
            CallResult callResult = callResultFuture10.get();
            LOG.info("Result: {}", callResult);
            assertNotNull(callResult);
            assertNotNull(callResult.getWaitingTime());
            assertNotNull(callResult.getCall());
            assertEquals(callMessage10, callResult.getCall());
            assertTrue(callResult.getWaitingTime() > 4500);
        } catch (InterruptedException | ExecutionException e) {
            fail(e.getMessage());
        }
    }

}
