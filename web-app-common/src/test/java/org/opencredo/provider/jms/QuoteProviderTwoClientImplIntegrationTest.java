package org.opencredo.provider.jms;


import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

public class QuoteProviderTwoClientImplIntegrationTest {

    QuoteProviderTwoClientImpl client;

    @Autowired
    JmsTemplate jmsTemaplte;

    String requestsQueueName = "requestsQueue";


    @Before
    public void setUp(){

    }

}
