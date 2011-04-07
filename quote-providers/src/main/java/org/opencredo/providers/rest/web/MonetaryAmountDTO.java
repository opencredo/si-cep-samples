package org.opencredo.providers.rest.web;

import java.math.BigDecimal;

public class MonetaryAmountDTO {

    private BigDecimal amount;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
