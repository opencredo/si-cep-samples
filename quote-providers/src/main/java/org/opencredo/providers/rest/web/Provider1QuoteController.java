package org.opencredo.providers.rest.web;

import org.opencredo.providers.common.service.QuoteService;
import org.opencredo.quote.domain.Car;
import org.opencredo.quote.domain.MonetaryAmount;
import org.opencredo.quote.domain.Person;
import org.opencredo.quote.domain.Quote;
import org.opencredo.quote.domain.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Provider1QuoteController {

    public static final String PROVIDER_NAME = "Provider One";

    @Autowired
    private QuoteService quoteService;

    @RequestMapping(value = "/quote", method = RequestMethod.POST)
    public HttpEntity<CreateQuoteResponseDTO> createQuote(@RequestBody CreateQuoteRequestDTO request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Quote quote = quoteService.createQuote(PROVIDER_NAME, getPerson(request), getCar(request));
        ResponseEntity<CreateQuoteResponseDTO> response = new ResponseEntity<CreateQuoteResponseDTO>(mapToDTO(quote), headers, HttpStatus.CREATED);
        return response;
    }


    protected CreateQuoteResponseDTO mapToDTO(Quote quote) {
        CreateQuoteResponseDTO response = new CreateQuoteResponseDTO();

        CarDTO carDTO = new CarDTO();
        carDTO.setMakeAndModel(quote.getApplicantsCar().getMakeAndModel());
        carDTO.setRegistration(quote.getApplicantsCar().getRegistration());
        carDTO.setValueOfCar(
                new MonetaryAmountDTO(quote.getApplicantsCar().getValueOfCar().asBigDecimal()));
        response.setCarDTO(carDTO);

        PersonDTO personDTO = new PersonDTO();
        personDTO.setAge(quote.getApplicant().getAge());
        personDTO.setAnnualMileage(quote.getApplicant().getAnnualMileage());
        personDTO.setYearsOfNoClaimsBonus(quote.getApplicant().getYearsOfNoClaimsBonus());
        response.setAplicant(personDTO);

        response.setMonetaryAmountDTO(new MonetaryAmountDTO(quote.getQuotedAnnualPrice().asBigDecimal()));

        return response;
    }

    private Person getPerson(CreateQuoteRequestDTO request) {
        PersonDTO dto = request.getApplicant();
        return new Person(dto.getAge(), dto.getYearsOfNoClaimsBonus(), dto.getAnnualMileage());
    }

    private Car getCar(CreateQuoteRequestDTO request) {
        CarDTO dto = request.getCar();
        return new Car(dto.getRegistration(), dto.getMakeAndModel(),
                new MonetaryAmount(dto.getValueOfCar().getAmount()));
    }
}
