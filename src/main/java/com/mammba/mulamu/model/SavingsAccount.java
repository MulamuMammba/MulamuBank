package com.mammba.mulamu.model;

public class SavingsAccount extends BankAccount {
    public SavingsAccount(int accountNum, String holder, double balance) {
        super(accountNum, holder, balance);
    }

    @Override
    public void setAccountHolder(String accountHolder) {
        super.setAccountHolder(accountHolder);
    }
}
