package org.opencredo.quote.domain;


import java.io.Serializable;

public class Car implements Serializable {

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

    @Override
    public String toString() {
        return "Car{" +
                "registration='" + registration + '\'' +
                ", makeAndModel='" + makeAndModel + '\'' +
                ", valueOfCar=" + valueOfCar +
                '}';
    }
}
