package org.opencredo.provider.jms;

import org.opencredo.quote.domain.*;

import java.math.BigDecimal;

public class TestData {

    public static final double quotedPrice = 555.55;

    public static final String testProvider = "testProviderName";

    public static Car buildTestCar(){
        return new Car(carReg,carMakeAndModel,carValue);
    }

    public static Person buildTestApplicant(){
        return new Person(applicantAge,applicantYearsOfNoClaims,applicantAnnualMileage);
    }

    public static QuoteRequest buildTestQuoteRequest(){
        return new QuoteRequest(buildTestCar(),buildTestApplicant());
    }

    public static Quote buildTestQuote(){
       return new Quote(testProvider, buildTestApplicant(), buildTestCar(), new MonetaryAmount(new BigDecimal(quotedPrice)));

    }

    public static final int applicantAge = 28;
    public static final int applicantYearsOfNoClaims=10;
    public static final int applicantAnnualMileage=1000;


    public static final String carReg = "T65 YHJ";
    public static final String carMakeAndModel = "FORD KA";
    public static final MonetaryAmount carValue = new MonetaryAmount(new BigDecimal(10000));
}
