package org.opencredo.quote.domain;


public class Quote {

    private final Person applicant;

    private final Car applicantsCar;

    private final MonetaryAmount quotedAnnualPrice;

    private final String providerName;

    public Quote( String providerName, Person applicant, Car applicantsCar, MonetaryAmount quotedAnnualPrice) {
        this.applicant = applicant;
        this.applicantsCar = applicantsCar;
        this.quotedAnnualPrice = quotedAnnualPrice;
        this.providerName = providerName;
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

    public String getProviderName() {
        return providerName;
    }
}
