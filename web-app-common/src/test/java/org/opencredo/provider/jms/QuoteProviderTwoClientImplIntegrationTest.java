package org.opencredo.provider.jms;

import static org.opencredo.provider.TestData.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.opencredo.quote.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jms.core.JmsOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.JMSException;
import javax.jms.Message;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class QuoteProviderTwoClientImplIntegrationTest {

    @Autowired
    QuoteProviderTwoClientImpl client;

    @Autowired
    JmsOperations jmsOperations;

    TaskExecutor executor = new SimpleAsyncTaskExecutor();


    String requestsQueueName = "requestsQueue";


    final long defaultTimeout = 5000;

    @Before
    public void setUp() {

    }

    @Test
    public void testSuccessfultSendAndReceive() throws Exception{
        TestRunnable r = new TestRunnable(buildTestQuote(), jmsOperations, defaultTimeout);
        executor.execute(r);
        Quote q = client.getQuote(buildTestQuoteRequest());
        assertNotNull("No request received on expected queue", r.received);
    }





    public static class TestRunnable implements Runnable {


        public Message received;

        private Object responsePayload;

        private JmsOperations jmsOperations;

        private long timeoutMillis;

        public TestRunnable(Object responsePayload, JmsOperations jmsOperations, long timeoutMillis) {
            this.responsePayload = responsePayload;
            this.jmsOperations = jmsOperations;
            this.timeoutMillis = timeoutMillis;
        }

        @Override
        public void run() {
            long start = System.currentTimeMillis();
            boolean responded = false;
            while ((System.currentTimeMillis() < (start + timeoutMillis))
                    && !responded) {
                received = jmsOperations.receive();
                try {
                    if (received != null) {
                        jmsOperations.convertAndSend(received.getJMSReplyTo(), responsePayload);
                        responded = true;
                    }
                } catch (JMSException jmsE) {
                    new RuntimeException(jmsE);
                }
            }
        }
    }


}
