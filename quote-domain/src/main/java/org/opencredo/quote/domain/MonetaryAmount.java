package org.opencredo.quote.domain;


import java.io.Serializable;
import java.math.BigDecimal;

public class MonetaryAmount implements Serializable {

    private final BigDecimal amount;

    public MonetaryAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public MonetaryAmount(String amount){
        this.amount = new BigDecimal(amount);
    }

    public BigDecimal asBigDecimal(){
        return amount;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MonetaryAmount that = (MonetaryAmount) o;

        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return amount != null ? amount.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "MonetaryAmount{" +
                "amount=" + amount +
                '}';
    }
}

