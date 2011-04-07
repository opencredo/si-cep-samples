package org.opencredo.providers.rest.web;

import org.opencredo.providers.common.service.QuoteService;
import org.opencredo.quote.domain.Car;
import org.opencredo.quote.domain.MonetaryAmount;
import org.opencredo.quote.domain.Person;
import org.opencredo.quote.domain.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Provider1QuoteController {

    @Autowired
    private QuoteService quoteService;

    @RequestMapping(value = "/quote", method = RequestMethod.POST)
    public HttpEntity<Quote> createQuote(@RequestBody CreateQuoteRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Quote quote = quoteService.createQuote(getPerson(request), getCar(request));
        ResponseEntity<Quote> response = new ResponseEntity<Quote>(quote, HttpStatus.CREATED);
        return response;
    }

    private Person getPerson(CreateQuoteRequest request) {
        PersonDTO dto = request.getApplicant();
        return new Person(dto.getAge(), dto.getYearsOfNoClaimsBonus(), dto.getAnnualMileage());
    }

    private Car getCar(CreateQuoteRequest request) {
        CarDTO dto = request.getCar();
        return new Car(dto.getRegistration(), dto.getMakeAndModel(),
                new MonetaryAmount(dto.getValueOfCar().getAmount()));
    }
}
