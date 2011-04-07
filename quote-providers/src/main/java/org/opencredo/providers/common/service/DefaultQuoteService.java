package org.opencredo.providers.common.service;

import org.opencredo.quote.domain.Car;
import org.opencredo.quote.domain.MonetaryAmount;
import org.opencredo.quote.domain.Person;
import org.opencredo.quote.domain.Quote;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class DefaultQuoteService implements QuoteService {

    private final Random quoteAmountGenerator = new Random();
    private final AtomicLong sequence = new AtomicLong(0);
    private final Map<Long, Quote> quotes = new ConcurrentHashMap<Long, Quote>();

    public Quote createQuote(Person applicant, Car car) {
        MonetaryAmount quotedAnnualPrice = new MonetaryAmount(
                new BigDecimal(quoteAmountGenerator.nextInt()));
        Quote quote = new Quote(applicant, car, quotedAnnualPrice);
        quotes.put(sequence.incrementAndGet(), quote);
        return quote;
    }
}
