package org.opencredo.quote.domain;

public class Person {

    private final int age;

    private final int yearsOfNoClaimsBonus;

    private final int annualMileage;

    //ignore gender due to EU


    public Person(int age, int yearsOfNoClaimsBonus, int annualMileage) {
        this.age = age;
        this.yearsOfNoClaimsBonus = yearsOfNoClaimsBonus;
        this.annualMileage = annualMileage;
    }

    public int getAge() {
        return age;
    }

    public int getYearsOfNoClaimsBonus() {
        return yearsOfNoClaimsBonus;
    }

    public int getAnnualMileage() {
        return annualMileage;
    }
}
