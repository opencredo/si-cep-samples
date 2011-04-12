package org.opencredo.web;

import org.opencredo.provider.QuoteProviderClient;
import org.opencredo.provider.web.GetQuoteCommand;
import org.opencredo.quote.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class QuoteController {

    @Autowired
    @Qualifier("providerOneClient")
    private QuoteProviderClient providerOneClient;

    @Autowired
    @Qualifier("providerTwoClient")
    private QuoteProviderClient providerTwoClient;


    @RequestMapping("/getquote")
    public String getQuote(@ModelAttribute GetQuoteCommand command, Model model) {
        List<Quote> quotes = new ArrayList<Quote>();
        quotes.add(providerOneClient.getQuote(buildQuoteRequest(command)));
        quotes.add(providerTwoClient.getQuote(buildQuoteRequest(command)));
        model.addAttribute("quotes", quotes);
        System.out.println("Quotes" + quotes);
        return "showquotes";
    }

    @RequestMapping("/showquoteform")
    public String showQuoteForm(Model model) {
        model.addAttribute("getQuotesCommand", new GetQuoteCommand());
        return "quoteform";
    }

    protected QuoteRequest buildQuoteRequest(GetQuoteCommand command) {
        Car car = new Car(command.getCarReg(), command.getMakeAndModel(),
                new MonetaryAmount(new BigDecimal(command.getCarValue())));
        Person applicant = new Person(command.getAge(), command.getYearsNoClaims(),
                command.getAnnualMileage());
        return new QuoteRequest(car, applicant);
    }

}
