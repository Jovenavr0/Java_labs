package ru.Jovenavr0.Aplication;

import ru.Jovenavr0.Aplication.ATMException.NoLoginException;
import ru.Jovenavr0.Aplication.ATMException.NotEnoughMoney;
import ru.Jovenavr0.Aplication.ATMException.NotPositiveQuantity;
import ru.Jovenavr0.Domain.Card;
import ru.Jovenavr0.Domain.Transaction;

import java.util.List;

public class ATMService {

    private boolean is_working;
    public ATMService() {
        is_working = false;
    }

    public Card CreateAccount(long cardNumber, double cardBalance) {
        is_working = true;
        return new Card(cardNumber, cardBalance);
    }
    public double CheckBalance(Card currentCard) throws NoLoginException {

        NeedLogin();

        return currentCard.GetBalance();
    }
    public double RefillBalance(Card currentCard, long amountDeposit) throws NotPositiveQuantity, NoLoginException {

        NeedLogin();
        CheckPositiveQuantity(amountDeposit);

        return currentCard.RefillBalance(amountDeposit);
    }
    public double WithdrawBalance(Card currentCard, long amountDeposit) throws NotPositiveQuantity, NoLoginException, NotEnoughMoney {

        NeedLogin();
        CheckPositiveQuantity(amountDeposit);

        return currentCard.WithdrawBalance(amountDeposit);
    }

    public List<Transaction> CheckOperationHistory(Card currentCard) throws NoLoginException {
        NeedLogin();
        return currentCard.GetTransactions();
    }

    private void CheckPositiveQuantity(long amount) throws NotPositiveQuantity {
        if (amount < 0) {
            throw new NotPositiveQuantity("Enter a positive amount.");
        }
    }

    private void NeedLogin() throws NoLoginException {
        if (!is_working){
            throw new NoLoginException("You are not logged in.");
        }
    }

    public boolean Is_working() {
        return is_working;
    }
}
