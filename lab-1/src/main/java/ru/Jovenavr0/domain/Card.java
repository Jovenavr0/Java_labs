package ru.Jovenavr0.Domain;

import ru.Jovenavr0.Aplication.ATMException.NotEnoughMoney;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Card {

    private UUID CardId;

    private long CardNumber;

    private double Balance;

    private List<Transaction> Transactions;

    public List<Transaction> GetTransactions() {

        Transaction transaction = new Transaction("Get Transactions", 0);
        Transactions.add(transaction);

        return Transactions;
    }

    public double GetBalance() {

        Transaction transaction = new Transaction("Get Balance", 0);
        Transactions.add(transaction);

        return Balance;
    }

    public double RefillBalance(long amount) {

        Transaction transaction = new Transaction("Refill Balance", amount);
        Transactions.add(transaction);

        Balance += amount;
        return Balance;
    }

    public double WithdrawBalance(long amount) throws NotEnoughMoney {

        if (Balance < amount) {
            throw new NotEnoughMoney("No enough money.");
        }

        Transaction transaction = new Transaction("Withdraw Balance", amount);
        Transactions.add(transaction);

        Balance -= amount;
        return Balance;
    }

    public Card(long CardNumber, double Balance) {
        CardId = UUID.randomUUID();
        this.CardNumber = CardNumber;
        this.Balance = Balance;
        Transactions = new ArrayList<Transaction>();
    }
}
