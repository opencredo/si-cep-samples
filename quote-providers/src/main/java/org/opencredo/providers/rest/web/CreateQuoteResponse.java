package org.opencredo.providers.rest.web;

public class CreateQuoteResponse {


    private CarDTO carDTO;

    private PersonDTO aplicant;

    private MonetaryAmountDTO monetaryAmountDTO;

    public CarDTO getCarDTO() {
        return carDTO;
    }

    public void setCarDTO(CarDTO carDTO) {
        this.carDTO = carDTO;
    }

    public PersonDTO getAplicant() {
        return aplicant;
    }

    public void setAplicant(PersonDTO aplicant) {
        this.aplicant = aplicant;
    }

    public MonetaryAmountDTO getMonetaryAmountDTO() {
        return monetaryAmountDTO;
    }

    public void setMonetaryAmountDTO(MonetaryAmountDTO monetaryAmountDTO) {
        this.monetaryAmountDTO = monetaryAmountDTO;
    }
}
