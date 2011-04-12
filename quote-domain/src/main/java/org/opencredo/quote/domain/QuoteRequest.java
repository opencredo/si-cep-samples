package org.opencredo.quote.domain;


import java.io.Serializable;

public class QuoteRequest implements Serializable{

    private final Car car;

    private final Person applicant;

    public QuoteRequest(Car car, Person applicant) {
        this.car = car;
        this.applicant = applicant;
    }

    public Car getCar() {
        return car;
    }

    public Person getApplicant() {
        return applicant;
    }
}

