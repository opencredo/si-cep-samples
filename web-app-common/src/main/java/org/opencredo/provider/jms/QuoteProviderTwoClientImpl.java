package org.opencredo.provider.jms;


import org.opencredo.provider.QuoteProviderClient;
import org.opencredo.provider.QuoteProviderResponseTimeout;
import org.opencredo.quote.domain.Quote;
import org.opencredo.quote.domain.QuoteRequest;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.jms.core.SessionCallback;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Session;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;

public class QuoteProviderTwoClientImpl implements QuoteProviderClient {

    private final String requestQueueName;

    private final JmsOperations jmsOperations;

    public QuoteProviderTwoClientImpl(String requestQueueName, JmsOperations jmsOperations) {
        this.requestQueueName = requestQueueName;
        this.jmsOperations = jmsOperations;
    }

    @Override
    public Quote getQuote(final QuoteRequest quoteRequest) {
        Quote q = this.jmsOperations.execute(new SessionCallback<Quote>() {
            @Override
            public Quote doInJms(Session session) throws JMSException {
                Document doc = buildRequestDocument(quoteRequest);
                final Queue tempReplyQueue = session.createTemporaryQueue();
                jmsOperations.convertAndSend(QuoteProviderTwoClientImpl.this.requestQueueName, doc, new MessagePostProcessor() {
                    @Override
                    public Message postProcessMessage(Message message) throws JMSException {
                        message.setJMSReplyTo(tempReplyQueue);
                        return message;
                    }
                });
                return (Quote)jmsOperations.receiveAndConvert(tempReplyQueue);

            }
        });
        if(q == null){
            throw new QuoteProviderResponseTimeout("No response received from provider two");
        }
        return q;

    }


    public Document buildRequestDocument(QuoteRequest quoteRequest) {
        StringBuffer request = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        request.append("<quoteRequest><car reg='")
                .append(quoteRequest.getCar().getRegistration()).append("' makeAndModel='")
                .append(quoteRequest.getCar().getMakeAndModel()).append("' ")
                .append("value='").append(quoteRequest.getCar().getValueOfCar().asBigDecimal())
                .append("' /><applicant age='").append(quoteRequest.getApplicant().getAge())
                .append("' yearsNoClaims='").append(quoteRequest.getApplicant().getYearsOfNoClaimsBonus())
                .append("' ").append("annualMileage='").append(quoteRequest.getApplicant().getAnnualMileage())
                .append("' /></quoteRequest>");

        Document doc = null;
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            doc = builder.parse(new InputSource(new StringReader(request.toString())));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return doc;


    }


}
