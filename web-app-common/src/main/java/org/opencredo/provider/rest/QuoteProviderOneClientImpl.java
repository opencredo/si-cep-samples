package org.opencredo.provider.rest;

import org.opencredo.provider.QuoteProviderClient;
import org.opencredo.quote.domain.*;
import org.opencredo.quote.domain.dto.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestOperations;

import java.net.URI;

public class QuoteProviderOneClientImpl implements QuoteProviderClient {

    private final URI baseUri;

    private final RestOperations restOperations;

    public QuoteProviderOneClientImpl(URI baseUri, RestOperations restOperations) {
        this.baseUri = baseUri;
        this.restOperations = restOperations;
    }

    @Override
    public Quote getQuote(QuoteRequest quoteRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        CreateQuoteRequestDTO quoteRequestDto = mapToRequestDto(quoteRequest);
        HttpEntity<CreateQuoteRequestDTO> entityRequest =
                new HttpEntity<CreateQuoteRequestDTO>(quoteRequestDto, headers);
        CreateQuoteResponseDTO responseDto = restOperations.postForEntity(baseUri, entityRequest, CreateQuoteResponseDTO.class).getBody();

        return mapDtoResponseToQuote(responseDto);
    }


    protected CreateQuoteRequestDTO mapToRequestDto(QuoteRequest quoteRequest) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setAge(quoteRequest.getApplicant().getAge());
        personDTO.setAnnualMileage(quoteRequest.getApplicant().getAnnualMileage());
        personDTO.setYearsOfNoClaimsBonus(quoteRequest.getApplicant().getYearsOfNoClaimsBonus());
        CarDTO carDTO = new CarDTO();
        carDTO.setMakeAndModel(quoteRequest.getCar().getMakeAndModel());
        carDTO.setRegistration(quoteRequest.getCar().getRegistration());
        carDTO.setValueOfCar(
                new MonetaryAmountDTO(quoteRequest.getCar().getValueOfCar().asBigDecimal()));
        CreateQuoteRequestDTO createQuoteRequest = new CreateQuoteRequestDTO();
        createQuoteRequest.setApplicant(personDTO);
        createQuoteRequest.setCar(carDTO);
        return createQuoteRequest;
    }

    protected Quote mapDtoResponseToQuote(CreateQuoteResponseDTO response){
        CarDTO carDTO = response.getCarDTO();
        Car car = new Car(carDTO.getRegistration(),carDTO.getMakeAndModel(),
                new MonetaryAmount(carDTO.getValueOfCar().getAmount()));
        PersonDTO personDto = response.getAplicant();
        Person person = new Person(personDto.getAge(),personDto.getYearsOfNoClaimsBonus()
                , personDto.getAnnualMileage());
         Quote quote = new Quote(response.getProviderName(),person, car,
                 new MonetaryAmount(response.getMonetaryAmountDTO().getAmount()));
        return quote;



    }
}
