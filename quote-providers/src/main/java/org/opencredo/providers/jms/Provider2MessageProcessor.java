package org.opencredo.providers.jms;


import org.opencredo.providers.common.service.QuoteService;
import org.opencredo.quote.domain.Car;
import org.opencredo.quote.domain.MonetaryAmount;
import org.opencredo.quote.domain.Person;
import org.opencredo.quote.domain.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.xml.xpath.XPathExpression;
import org.springframework.xml.xpath.XPathExpressionFactory;
import org.w3c.dom.Document;

@Service
public class Provider2MessageProcessor {

    public static final String PROVIDER_NAME= "Provider Two";

    private final QuoteService quoteService;

    @Autowired
    public Provider2MessageProcessor(@Qualifier("fixedRateQuoteService") QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    public Quote getQuote(Document document) {
        System.out.println("Request to get a quote received");
        Person applicant = getPerson(document);
        Car car = getCar(document);
        return quoteService.createQuote(PROVIDER_NAME, applicant, car);
    }

    private Car getCar(Document document) {
        String carReg = getCarReg.evaluateAsString(document);
        String carMakeAndModel = getCarMakeAndModel.evaluateAsString(document);
        MonetaryAmount carValue = new MonetaryAmount(getCarValue.evaluateAsString(document));
        return new Car(carReg, carMakeAndModel, carValue);
    }

    private Person getPerson(Document document) {
        int age = Integer.parseInt(getAge.evaluateAsString(document));
        int yearsNoClaims = Integer.parseInt(getYearsNoClaims.evaluateAsString(document));
        int annualMileage = Integer.parseInt(getAnnualMileage.evaluateAsString(document));

        return new Person(age, yearsNoClaims, annualMileage);
    }

    private final XPathExpression getAge = XPathExpressionFactory.createXPathExpression("string(/quoteRequest/applicant/@age)");
    private final XPathExpression getAnnualMileage = XPathExpressionFactory.createXPathExpression("string(/quoteRequest/applicant/@annualMileage)");
    private final XPathExpression getYearsNoClaims = XPathExpressionFactory.createXPathExpression("string(/quoteRequest/applicant/@yearsNoClaims)");

    private final XPathExpression getCarValue = XPathExpressionFactory.createXPathExpression("string(/quoteRequest/car/@value)");
    private final XPathExpression getCarMakeAndModel = XPathExpressionFactory.createXPathExpression("string(/quoteRequest/car/@makeAndModel)");
    private final XPathExpression getCarReg = XPathExpressionFactory.createXPathExpression("string(/quoteRequest/car/@reg)");

}
