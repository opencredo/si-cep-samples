package org.opencredo.provider;

import org.opencredo.quote.domain.Quote;
import org.opencredo.quote.domain.QuoteRequest;

import java.util.List;

public interface QuoteClientService {

    public List<Quote> getQuotes(QuoteRequest quoteRequest);

}
