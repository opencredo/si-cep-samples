package org.opencredo.providers.rest.web;

import org.opencredo.quote.domain.MonetaryAmount;

public class CarDTO {

    private String registration;

    private String makeAndModel;

    private MonetaryAmountDTO valueOfCar;

    public String getMakeAndModel() {
        return makeAndModel;
    }

    public void setMakeAndModel(String makeAndModel) {
        this.makeAndModel = makeAndModel;
    }

    public MonetaryAmountDTO getValueOfCar() {
        return valueOfCar;
    }

    public void setValueOfCar(MonetaryAmountDTO valueOfCar) {
        this.valueOfCar = valueOfCar;
    }

    public String getRegistration() {

        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }



}
