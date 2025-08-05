package ru.Jovenavr0;

import org.junit.jupiter.api.Assertions;

import ru.Jovenavr0.Aplication.ATMException.NotPositiveQuantity;
import ru.Jovenavr0.Aplication.ATMException.NoLoginException;
import ru.Jovenavr0.Aplication.ATMException.NotEnoughMoney;
import ru.Jovenavr0.Aplication.ATMService;
import ru.Jovenavr0.Domain.Card;

import org.junit.jupiter.api.Test;

public class Tests {

    @Test
    public void CheckCreateAccountExpectedSuccess() {
        ATMService atm = new ATMService();
        atm.CreateAccount(1234123412341234L, 1000);
        Assertions.assertTrue(atm.Is_working());
    }

    @Test
    public void CheckCreateAccountExpectedFailure() {
        ATMService atm = new ATMService();
        Assertions.assertFalse(atm.Is_working());
    }

    @Test
    public void CheckRefillBalanceExpectedSuccess() throws NoLoginException, NotPositiveQuantity {
        ATMService atm = new ATMService();
        long start_balance = 1000;
        atm.CreateAccount(1234123412341234L, start_balance);
        Card card = new Card(1234123412341234L, start_balance);
        long amount = 500;
        Assertions.assertEquals(start_balance + amount, atm.RefillBalance(card, amount));
    }

    @Test
    public void CheckRefillBalanceWithoutLogInExpectedFailure() {
        ATMService atm = new ATMService();
        long start_balance = 1000;
        Card card = new Card(1234123412341234L, start_balance);
        long amount = 500;
        Assertions.assertThrows(NoLoginException.class, () -> atm.RefillBalance(card, amount));
    }

    @Test
    public void CheckRefillBalanceWithNegativeQuantityExpectedFailure() {
        ATMService atm = new ATMService();
        long start_balance = 1000;
        atm.CreateAccount(1234123412341234L, start_balance);
        Card card = new Card(1234123412341234L, start_balance);
        long amount = -500;
        Assertions.assertThrows(NotPositiveQuantity.class, () -> atm.RefillBalance(card, amount));
    }

    @Test
    public void CheckWithdrawBalanceExpectedSuccess() throws NoLoginException, NotPositiveQuantity, NotEnoughMoney {
        ATMService atm = new ATMService();
        long start_balance = 1000;
        atm.CreateAccount(1234123412341234L, start_balance);
        Card card = new Card(1234123412341234L, start_balance);
        long amount = 500;
        Assertions.assertEquals(start_balance - amount, atm.WithdrawBalance(card, amount));
    }

    @Test
    public void CheckWithdrawBalanceWithoutLogInExpectedFailure() {
        ATMService atm = new ATMService();
        long start_balance = 1000;
        Card card = new Card(1234123412341234L, start_balance);
        long amount = 500;
        Assertions.assertThrows(NoLoginException.class, () -> atm.WithdrawBalance(card, amount));
    }

    @Test
    public void CheckWithdrawBalanceWithNegativeQuantityExpectedFailure() {
        ATMService atm = new ATMService();
        long start_balance = 1000;
        atm.CreateAccount(1234123412341234L, start_balance);
        Card card = new Card(1234123412341234L, start_balance);
        long amount = -500;
        Assertions.assertThrows(NotPositiveQuantity.class, () -> atm.WithdrawBalance(card, amount));
    }

    @Test
    public void CheckWithdrawBalanceMoreMoneyThanHaveExpectedFailure() {
        ATMService atm = new ATMService();
        long start_balance = 1000;
        atm.CreateAccount(1234123412341234L, start_balance);
        Card card = new Card(1234123412341234L, start_balance);
        long amount = 1001;
        Assertions.assertThrows(NotEnoughMoney.class, () -> atm.WithdrawBalance(card, amount));
    }

    @Test
    public void CheckBalanceExpectedSuccess() throws NoLoginException {
        ATMService atm = new ATMService();
        long start_balance = 1000;
        atm.CreateAccount(1234123412341234L, start_balance);
        Card card = new Card(1234123412341234L, start_balance);
        Assertions.assertEquals(start_balance, atm.CheckBalance(card));
    }

    @Test
    public void CheckBalanceWithoutLogInExpectedFailure() {
        ATMService atm = new ATMService();
        long start_balance = 1000;
        Card card = new Card(1234123412341234L, start_balance);
        Assertions.assertThrows(NoLoginException.class, () -> atm.CheckBalance(card));
    }

    @Test
    public void CheckOperationHistoryWithoutLogInExpectedFailure() {
        ATMService atm = new ATMService();
        long start_balance = 1000;
        Card card = new Card(1234123412341234L, start_balance);
        Assertions.assertThrows(NoLoginException.class, () -> atm.CheckOperationHistory(card));
    }

}
