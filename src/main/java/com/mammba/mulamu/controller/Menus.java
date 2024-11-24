package com.mammba.mulamu.controller;


import com.mammba.mulamu.database.AccountsDatabase;

import java.util.Scanner;

public class Menus {
    Scanner input = new Scanner(System.in);
    public Menus(AccountsDatabase database){

        System.out.println("""
                What do you want to do today?\
                
                1)View Accounts🏧
                2)New Account🏦
                3)Transfer💸
                4)Deposit💰
                5)Withdraw🤑
                6) Exit😢
                
                """);

        String ans = input.next();

        switch (ans){
            case "1" -> viewAccounts(database);
            case "2" -> ConsoleAccountManagers.createAccount(database);
            case "3" -> transferMoney(database);
            case "4" -> depositMoney(database);
            case "5" -> withdrawMoney(database);
            case "6" -> ConsoleAccountManagers.exit();
            default -> {
                System.out.println("Invalid option please try again");
                new Menus(database);
            }
        }
    }

    private void withdrawMoney(AccountsDatabase database) {
    }

    private void depositMoney(AccountsDatabase database) {
    }

    private void transferMoney(AccountsDatabase database) {
    }

    private void viewAccounts(AccountsDatabase database) {
    }

}
