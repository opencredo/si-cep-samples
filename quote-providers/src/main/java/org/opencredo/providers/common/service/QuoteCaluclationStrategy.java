package org.opencredo.providers.common.service;


import org.opencredo.quote.domain.MonetaryAmount;

public interface QuoteCaluclationStrategy {

    public MonetaryAmount getQuoteAmount();

}
