package org.opencredo.quote.domain.dto;

public class CreateQuoteResponseDTO {


    private CarDTO carDTO;

    private PersonDTO aplicant;

    private MonetaryAmountDTO monetaryAmountDTO;

    private String providerName;

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

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }
}
