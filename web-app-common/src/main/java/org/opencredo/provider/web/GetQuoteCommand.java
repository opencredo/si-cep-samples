package org.opencredo.provider.web;


public class GetQuoteCommand {

    public int age = 25;

    public int annualMileage = 5000;

    public int yearsNoClaims = 0;

    public String carReg = "ABC123";

    public String makeAndModel = "Ford KA";

    public double carValue = 1000;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public int getAnnualMileage() {
        return annualMileage;
    }

    public void setAnnualMileage(int annualMileage) {
        this.annualMileage = annualMileage;
    }

    public int getYearsNoClaims() {
        return yearsNoClaims;
    }

    public void setYearsNoClaims(int yearsNoClaims) {
        this.yearsNoClaims = yearsNoClaims;
    }

    public String getCarReg() {
        return carReg;
    }

    public void setCarReg(String carReg) {
        this.carReg = carReg;
    }

    public String getMakeAndModel() {
        return makeAndModel;
    }

    public void setMakeAndModel(String makeAndModel) {
        this.makeAndModel = makeAndModel;
    }

    public double getCarValue() {
        return carValue;
    }

    public void setCarValue(double carValue) {
        this.carValue = carValue;
    }
}
