package org.opencredo.providers.common.service;

import org.opencredo.quote.domain.MonetaryAmount;

import java.math.BigDecimal;
import java.util.Random;

public class FixedRateQuoteCalculationStrategy implements QuoteCaluclationStrategy {

    private final Random rand = new Random();

    private long sleepInMillis = 2000;

    public synchronized MonetaryAmount getQuoteAmount(){
        try{
            Thread.sleep(sleepInMillis);
            return new MonetaryAmount(new BigDecimal(rand.nextInt(1000)));
        } catch (Exception e){
            throw new RuntimeException(e);
        }


    }



}
