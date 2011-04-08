package org.opencredo.providers.jms;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsOperations;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;

public class JmsProviderTestClient {

    @Autowired
    private JmsOperations jmsOperations;

    private ClassPathXmlApplicationContext appCtx;

    private Logger logger = Logger.getLogger(getClass());


    public JmsProviderTestClient() {
        appCtx = new ClassPathXmlApplicationContext("classpath:providerTestClientAppCtx.xml");
        appCtx.refresh();
        appCtx.start();
        appCtx.getAutowireCapableBeanFactory().autowireBean(this);
    }


    public void sendTestJmsMessage() {
        this.jmsOperations.convertAndSend("quoteRequestQueue", getStandardSampleRequestDocument());
        logger.info("Response was " + this.jmsOperations.receiveAndConvert("quoteResponseQueue"));
    }

    public void shutdown() {
        appCtx.close();
    }


    public static void main(String[] args) {
        JmsProviderTestClient providerTestClient = new JmsProviderTestClient();
        providerTestClient.sendTestJmsMessage();
        providerTestClient.shutdown();
    }

    protected Document getStandardSampleRequestDocument() {
        try {
            return DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder().parse(new InputSource(new StringReader(sampleDocument)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static final String carReg = "AA12 ABC";

    private static final String makeAndModel = "CORSA";

    private static final int carValue = 5000;

    private static final int age = 25;

    private static final int yearsOfNoClaims = 10;

    private static final long annualMileage = 5000;

    private static String sampleDocument =
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                    "<quoteRequest>" +
                    "<car reg='" + carReg + "' makeAndModel='" + makeAndModel + "' " +
                    "value='" + carValue + "' />" +
                    "<applicant age='" + age + "' yearsNoClaims='" + yearsOfNoClaims + "' " +
                    "annualMileage='" + annualMileage + "' />" +
                    "</quoteRequest>";


}
