package org.opencredo.quote.domain.dto;

public class PersonDTO {
    private int age;

    private int yearsOfNoClaimsBonus;

    private int annualMileage;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getYearsOfNoClaimsBonus() {
        return yearsOfNoClaimsBonus;
    }

    public void setYearsOfNoClaimsBonus(int yearsOfNoClaimsBonus) {
        this.yearsOfNoClaimsBonus = yearsOfNoClaimsBonus;
    }

    public int getAnnualMileage() {
        return annualMileage;
    }

    public void setAnnualMileage(int annualMileage) {
        this.annualMileage = annualMileage;
    }
}
