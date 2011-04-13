package org.opencredo.flow;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.opencredo.provider.QuoteClientService;
import org.opencredo.provider.TestData;
import org.opencredo.quote.domain.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:quote-flow-integration-test.xml")
public class QuoteFlowIntegrationTest {

    @Autowired
    QuoteClientService quoteService;


    @Test
    public void endToEndTest(){
        List<Quote> quotes = quoteService.getQuotes(TestData.buildTestQuoteRequest());
        assertNotNull(quotes);
        assertEquals("Wrong number of quotes received ", 2, quotes.size());
    }




}
