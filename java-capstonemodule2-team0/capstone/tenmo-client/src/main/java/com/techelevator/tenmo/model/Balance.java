package com.techelevator.tenmo.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class Balance {
@JsonProperty("balance")

    public BigDecimal balance;

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void sendBucks(BigDecimal amountToSend) {
        BigDecimal newBalance = balance.subtract(amountToSend);
        this.balance = newBalance;
    }

    public void receiveBucks(BigDecimal amountToRecieve) {
        BigDecimal newBalance = balance.add(amountToRecieve);
        this.balance = newBalance;
    }
}

