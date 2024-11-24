package com.mammba.mulamu.controller.spring;

import com.mammba.mulamu.controller.Menus;
import com.mammba.mulamu.database.AccountsDatabase;
import com.mammba.mulamu.model.BankAccount;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

public class homepage {

    static AccountsDatabase da = new AccountsDatabase();
    @RestController
    public static class index{

        @GetMapping("/hi")
        public String hello(){


            List<BankAccount> accounts = da.getAllAccounts();
            if (accounts.isEmpty()) {
                return"Ohh it looks like you don't have any accounts with us, much sad." +
                        "\n Do you want to make one? Press E to exit or any other key to continue.";


            }
            else {
                return """
                        Hello and Welcome to the best console app ever🤩
                        
                        
                        """ + accounts.getFirst().getAccountHolder();
            }
        }
    }



}
