package com.mammba.mulamu.model;

public class BankAccount {
    private int accountNumber;
    private String accountHolder;
    private Double balance;

    public BankAccount(int accountNum, String holder, double balance) {
        setAccountHolder(holder);
        setAccountNumber(accountNum);
        setBalance(balance);
    }

    public void deposit(Double amount){

           balance += amount;
            //TODO Add to database
    }

    public boolean withdraw(Double amount){
        boolean success = false;

        if(this.balance<amount){

        }else {
            this.balance-= amount;
            success = true;
        }
        return success;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }
}


