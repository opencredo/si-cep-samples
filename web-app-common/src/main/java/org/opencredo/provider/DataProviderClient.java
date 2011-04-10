package org.opencredo.provider;


import org.opencredo.quote.domain.Quote;
import org.opencredo.quote.domain.QuoteRequest;

public interface DataProviderClient {

    public Quote getQuote(QuoteRequest quoteRequest);

}
