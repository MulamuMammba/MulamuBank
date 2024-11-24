package com.mammba.mulamu.controller;

import com.mammba.mulamu.database.AccountsDatabase;
import com.mammba.mulamu.model.BankAccount;

import java.util.List;
import java.util.Scanner;

public class ConsoleAccountManagers {
    private static Scanner input = new Scanner(System.in);
    public static void App(){

        AccountsDatabase database = new AccountsDatabase();
        System.out.println(
                "Hello and Welcome to the best console app ever🤩"
        );

        welcomeUser(database);

    }

    public static void welcomeUser(AccountsDatabase database){


        List<BankAccount> accounts = database.getAllAccounts();
        if (accounts.isEmpty()){
            System.out.println("Ohh it looks like you don't have any accounts with us, much sad." +
                    "\n Do you want to make one? Press E to exit or any other key to continue.");
             String ans = input.next();
             ans = ans.toLowerCase();

             if (ans.equals("e")){
                 exit();
             }else {

                 BankAccount account = createAccount(database);
                 System.out.println("First Account Successfully Created" +
                         "\n Thank you " + account.getAccountHolder());

                 exit();

             }
        }else if(accounts.size()==1){
            BankAccount current = accounts.get(0);

            System.out.println("""
                    One account found!!\
                    
                    Welcome back : """ + current.getAccountHolder()
            + "\n");

            new Menus(database);
        }else {
            System.out.println(
                    "Multiple Accounts found");
        }
    }

    public static void exit(){
        System.out.println(
                "GoodBye😭"
        );
        System.exit(1);
    }

    public static BankAccount createAccount(AccountsDatabase database){
        System.out.println("Enter account number: ");
        int accountNumber = input.nextInt();

        System.out.println("Enter account Holder name: ");
        String accountHolder = input.next();

        System.out.println("Enter the initial balance: ");
        double initBalance = input.nextDouble();


        database.addAccount(accountNumber,accountHolder,initBalance);

        return new BankAccount(accountNumber,accountHolder,initBalance);
    }
}
