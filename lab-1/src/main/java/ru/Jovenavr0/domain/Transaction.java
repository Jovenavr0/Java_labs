package ru.Jovenavr0.Domain;

import java.time.LocalDateTime;

public class Transaction {

    private final String transactionType;

    private final double amount;

    private final LocalDateTime dateTime;

    Transaction(String transactionType, double amount) {
        this.transactionType = transactionType;
        this.amount = amount;
        this.dateTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Transaction: " + "transactionType = " + transactionType + ", amount = " + amount + ", dateTime = " + dateTime + "\n";
    }

}
