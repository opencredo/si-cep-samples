package org.opencredo.providers.jms;

import org.opencredo.quote.domain.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestOperations;

import java.math.BigDecimal;

public class RestProviderTestClient {

    public static final String BASE_URL = "http://localhost:8080/quote-providers/rest/quote";

    private ClassPathXmlApplicationContext appCtx;

    @Autowired
    private RestOperations restOperations;

    public RestProviderTestClient() {
        appCtx = new ClassPathXmlApplicationContext("classpath:providerTestClientAppCtx.xml");
        appCtx.refresh();
        appCtx.start();
        appCtx.getAutowireCapableBeanFactory().autowireBean(this);
    }


    public void makeDefaultRestRequest(){
        PersonDTO personDTO = new PersonDTO();
        personDTO.setAge(age);
        personDTO.setAnnualMileage(annualMileage);
        personDTO.setYearsOfNoClaimsBonus(yearsOfNoClaims);
        CarDTO carDTO = new CarDTO();
        carDTO.setMakeAndModel(makeAndModel);
        carDTO.setRegistration(carReg);
        carDTO.setValueOfCar(new MonetaryAmountDTO(new BigDecimal(carValue)));

        CreateQuoteRequest createQuoteRequest = new CreateQuoteRequest();
        createQuoteRequest.setApplicant(personDTO);
        createQuoteRequest.setCar(carDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CreateQuoteRequest> entityRequest = new HttpEntity<CreateQuoteRequest>(createQuoteRequest, headers);
        restOperations.postForEntity(BASE_URL,entityRequest, CreateQuoteResponse.class);
    }

    public static void main(String[] args) {
        RestProviderTestClient testClient = new RestProviderTestClient();
        testClient.makeDefaultRestRequest();

    }

    private static final String carReg = "AA12 ABC";

    private static final String makeAndModel = "CORSA";

    private static final int carValue = 5000;

    private static final int age = 25;

    private static final int yearsOfNoClaims = 10;

    private static final int annualMileage = 5000;

}
