package org.opencredo.quote.domain.dto;

public class CreateQuoteRequestDTO {
    private PersonDTO applicant;
    private CarDTO car;

    public PersonDTO getApplicant() {
        return applicant;
    }

    public void setApplicant(PersonDTO applicant) {
        this.applicant = applicant;
    }

    public CarDTO getCar() {
        return car;
    }

    public void setCar(CarDTO car) {
        this.car = car;
    }
}
