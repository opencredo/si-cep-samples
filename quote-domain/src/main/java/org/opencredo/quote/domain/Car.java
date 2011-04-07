package org.opencredo.quote.domain;


public class Car {

    private final String registration;

    private final String makeAndModel;

    private final MonetaryAmount valueOfCar;

    public Car(String registration, String makeAndModel, MonetaryAmount valueOfCar) {
        this.registration = registration;
        this.makeAndModel = makeAndModel;
        this.valueOfCar = valueOfCar;
    }

    public String getRegistration() {
        return registration;
    }

    public String getMakeAndModel() {
        return makeAndModel;
    }

    public MonetaryAmount getValueOfCar() {
        return valueOfCar;
    }
}
