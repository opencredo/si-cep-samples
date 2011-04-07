package org.opencredo.quote.domain;


public class Quote {

    private final Person applicant;

    private final Car applicantsCar;

    private final MonetaryAmount quotedAnnualPrice;

    public Quote(Person applicant, Car applicantsCar, MonetaryAmount quotedAnnualPrice) {
        this.applicant = applicant;
        this.applicantsCar = applicantsCar;
        this.quotedAnnualPrice = quotedAnnualPrice;
    }

    public Person getApplicant() {
        return applicant;
    }

    public Car getApplicantsCar() {
        return applicantsCar;
    }

    public MonetaryAmount getQuotedAnnualPrice() {
        return quotedAnnualPrice;
    }
}
