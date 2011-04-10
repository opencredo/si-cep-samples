package org.opencredo.quote.domain.dto;

import java.math.BigDecimal;

public class MonetaryAmountDTO {

    private BigDecimal amount;

    public MonetaryAmountDTO() {
    }

    public MonetaryAmountDTO(BigDecimal amount){
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
