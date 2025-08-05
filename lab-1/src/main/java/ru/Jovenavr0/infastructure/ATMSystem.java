package ru.Jovenavr0.Infastructure;

import ru.Jovenavr0.Aplication.ATMException.NoLoginException;
import ru.Jovenavr0.Aplication.ATMException.NotEnoughMoney;
import ru.Jovenavr0.Aplication.ATMException.NotPositiveQuantity;
import ru.Jovenavr0.Aplication.ATMService;
import ru.Jovenavr0.Domain.Card;
import ru.Jovenavr0.Domain.Transaction;

import java.util.List;
import java.util.Scanner;

public class ATMSystem {

    private final Scanner scanner;

    private Card _currentCard;

    private final ATMService _atmService;

    private boolean is_working;

    public ATMSystem() {
        scanner = new Scanner(System.in);
        _atmService = new ATMService();
        is_working = true;
    }

    public void start(){

        System.out.println("\nWelcome to the ATM system!");

        int user_choice;

        while(is_working){
            ShowOptions();
            user_choice = GetChoice();
            SelectionHandler(user_choice);
        }

        scanner.close();

    }

    private void ShowOptions(){
        System.out.println("1. Create an account.");
        System.out.println("2. Check your balance.");
        System.out.println("3. Refill balance.");
        System.out.println("4. Withdraw balance.");
        System.out.println("5. View the operation history.");
        System.out.println("6. Finish the work.");
        System.out.println("Enter the action number: ");
    }

    private int GetChoice(){
        int choice = scanner.nextInt();
        scanner.nextLine();

        return choice;
    }

    private void SelectionHandler(int user_choice){

        switch (user_choice) {
            case 1:
                CreateAccountHandler();
                break;
            case 2:
                CheckBalanceHandler();
                break;
            case 3:
                RefillBalanceHandler();
                break;
            case 4:
                WithdrawBalanceHandler();
                break;
            case 5:
                CheckOperationHistoryHandler();
                break;
            case 6:
                FinishWork();
                break;
            default:
                System.out.println("wrong choice. Try again.");
        }
    }

    private void CheckOperationHistoryHandler() {

        try {

            List<Transaction> transactions = _atmService.CheckOperationHistory(_currentCard);

            System.out.println("Your bank card transaction history:");

            if (transactions.isEmpty()) {
                System.out.println("No transactions found.\n");
                return;
            }

            for (Transaction transaction : transactions) {
                System.out.println(transaction);
            }

        } catch (NoLoginException e) {
            System.out.println(e.getMessage());
        }
    }

    private void WithdrawBalanceHandler() {

        System.out.println("Enter the amount of money you want to withdraw: ");
        long amountDeposit = scanner.nextLong();

        try {

            double NewBalance = _atmService.WithdrawBalance(_currentCard, amountDeposit);
            System.out.println("You have successfully withdrew balance.");
            System.out.println("Your current balance is: " + NewBalance + "\n");

        } catch (NotPositiveQuantity | NotEnoughMoney | NoLoginException e) {
            System.out.println(e.getMessage());
        }
    }

    private void RefillBalanceHandler() {

        System.out.println("Enter the amount of money you want to add to your account: ");
        long amountDeposit = scanner.nextLong();

        try {

            double NewBalance = _atmService.RefillBalance(_currentCard, amountDeposit);
            System.out.println("You have successfully refilled balance.");
            System.out.println("Your current balance is: " + NewBalance + "\n");

        } catch (NotPositiveQuantity | NoLoginException e) {

            System.out.println(e.getMessage());

        }
    }

    private void CheckBalanceHandler() {

        try{
            double balance = _atmService.CheckBalance(_currentCard);
            System.out.println("Your balance: " + balance + "\n");
        } catch (NoLoginException e) {
            System.out.println(e.getMessage());
        }

    }

    private void FinishWork(){
        is_working = false;
        System.out.println("Thank you for choosing our bank!");
        System.out.println("You have successfully finished the work.");
    }

    private void CreateAccountHandler(){

        System.out.println("Make up and enter your card number: ");
        long cardNumber = scanner.nextLong();

        while (!IsValidCardNumber(cardNumber))
        {
            System.out.println("Please enter the card number consisting of 16 digits.");
            System.out.println("Make up and enter your card number: ");
            cardNumber = scanner.nextLong();
        }

        System.out.println("\nMake up and enter your balance: ");
        double cardBalance = scanner.nextDouble();

        while (!IsValidBalance(cardBalance))
        {
            System.out.println("\nPlease enter a positive card balance");
            System.out.println("\nMake up and enter your balance: ");
            cardBalance = scanner.nextLong();
        }

        _currentCard = _atmService.CreateAccount(cardNumber, cardBalance);
        System.out.println("\nYou have successfully created your account!");

    }

    private boolean IsValidCardNumber(long cardNumber){
        return String.valueOf(cardNumber).length() == 16;
    }

    private boolean IsValidBalance(double cardBalance){
        return cardBalance >= 0;
    }

}
