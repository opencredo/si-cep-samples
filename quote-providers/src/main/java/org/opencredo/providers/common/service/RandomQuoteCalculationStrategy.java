package org.opencredo.providers.common.service;

import org.opencredo.quote.domain.MonetaryAmount;

import java.math.BigDecimal;
import java.util.Random;

public class RandomQuoteCalculationStrategy implements QuoteCaluclationStrategy {

    private final Random rand = new Random();

    public MonetaryAmount getQuoteAmount() {
        return new MonetaryAmount(new BigDecimal(rand.nextInt(1000)));
    }


}
