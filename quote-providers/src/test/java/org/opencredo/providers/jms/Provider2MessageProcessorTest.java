package org.opencredo.providers.jms;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.opencredo.providers.common.service.QuoteService;
import org.opencredo.quote.domain.Car;
import org.opencredo.quote.domain.MonetaryAmount;
import org.opencredo.quote.domain.Person;
import org.opencredo.quote.domain.Quote;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.math.BigDecimal;

public class Provider2MessageProcessorTest {

    String testProvider = "testProvider";

    Provider2MessageProcessor messageProcessor;

    QuoteService mockQuoteService;

    BigDecimal defaultQuoteAmount = new BigDecimal(125.0);


    @Before
    public void setUp() {
        mockQuoteService = mock(QuoteService.class);
        messageProcessor = new Provider2MessageProcessor(mockQuoteService);
    }


    @Test
    public void testValidRequest() throws Exception {
        when(mockQuoteService.createQuote(any(String.class), any(Person.class), any(Car.class))).thenAnswer(new Answer<Quote>() {
            public Quote answer(InvocationOnMock invocation) throws Throwable {
                return new Quote((String) invocation.getArguments()[0],
                        (Person) invocation.getArguments()[1],
                        (Car) invocation.getArguments()[2],
                        new MonetaryAmount(defaultQuoteAmount));
            }
        });
        Quote returnedQuote = messageProcessor.getQuote(getStandardSampleRequestDocument());
        assertPersonIsDefault(returnedQuote.getApplicant());
        assertCarIsDefault(returnedQuote.getApplicantsCar());
        assertEquals("Wrong amount for quote", new MonetaryAmount(defaultQuoteAmount),
                returnedQuote.getQuotedAnnualPrice());
    }


    protected void assertPersonIsDefault(Person personToCompareToDefault) {
        assertNotNull("Person should not be null", personToCompareToDefault);
        assertEquals("Wrong age for person", age, personToCompareToDefault.getAge());
        assertEquals("Wrong number of no claims years",
                yearsOfNoClaims, personToCompareToDefault.getYearsOfNoClaimsBonus());
        assertEquals("Wrong annual mileage",
                annualMileage, personToCompareToDefault.getAnnualMileage());
    }


    protected void assertCarIsDefault(Car carToCompareToDefault) {
        assertNotNull("Car should not be null", carToCompareToDefault);
        assertEquals("Wrong reg for car", carReg, carToCompareToDefault.getRegistration());
        assertEquals("Wrong make and model for car",
                makeAndModel, carToCompareToDefault.getMakeAndModel());
        assertEquals("Wrong value for car",
                new MonetaryAmount(new BigDecimal(carValue)), carToCompareToDefault.getValueOfCar());
    }

    protected Document getStandardSampleRequestDocument() throws Exception {
        return DocumentBuilderFactory.newInstance()
                .newDocumentBuilder().parse(new InputSource(new StringReader(sampleDocument)));
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