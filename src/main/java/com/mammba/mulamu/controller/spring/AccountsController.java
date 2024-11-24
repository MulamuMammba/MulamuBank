package com.mammba.mulamu.controller.spring;

import com.mammba.mulamu.database.AccountsDatabase;
import com.mammba.mulamu.model.BankAccount;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AccountsController {

    private final AccountsDatabase database = new AccountsDatabase();

    @GetMapping("/welcome")
    public String welcomePage() {
        return "welcome"; // New Thymeleaf welcome template
    }

    @GetMapping("/")
    public String redirectToWelcome() {
        return "redirect:/welcome";
    }

    @GetMapping("/list")
    public String listAllAccounts(Model model) {
        List<BankAccount> accounts = database.getAllAccounts();
        model.addAttribute("accounts", accounts);
        return "list";
    }

    @GetMapping("/details/{accountNumber}")
    public String viewAccountDetails(@PathVariable int accountNumber, Model model) {
        BankAccount account = database.getAccountDetails(accountNumber);
        if (account != null) {
            model.addAttribute("account", account);
            return "account-details"; // This will display details of the selected account
        } else {
            model.addAttribute("message", "Account not found.");
            return "status"; // Show a status message if account is not found
        }
    }


    @GetMapping("/create")
    public String createAccountForm() {
        return "create-user"; // Form for creating a new account
    }

    @PostMapping("/create")
    public String createAccount(
            @RequestParam("accountNumber") int accountNumber,
            @RequestParam("accountHolder") String accountHolder,
            @RequestParam("balance") double balance,
            Model model) {

        database.addAccount(accountNumber, accountHolder, balance);
        model.addAttribute("message", "Account created successfully!");
        return "status"; // Status message page
    }

    @GetMapping("/deposit")
    public String depositForm(Model model) {
        List<BankAccount> accounts = database.getAllAccounts(); // Assuming a method that returns all accounts
        model.addAttribute("accounts", accounts); // Add the list of accounts to the model
        return "deposit"; // Send the model to the "deposit" view
    }

    @PostMapping("/deposit")
    public String deposit(@RequestParam("accountNumber") int accountNumber,
                          @RequestParam("amount") double amount,
                          Model model) {
        BankAccount account = database.getAccountDetails(accountNumber);
        if (account != null) {
            account.deposit(amount);
            database.modifyAccount(account.getAccountNumber(), account.getAccountHolder(), account.getBalance());
            model.addAttribute("message", "Deposit successful! New Balance: " + account.getBalance());
        } else {
            model.addAttribute("message", "Account not found.");
        }
        return "status";
    }

    @GetMapping("/withdraw")
    public String withdrawForm(Model model) {
        List<BankAccount> accounts = database.getAllAccounts();
        model.addAttribute("accounts", accounts);
        return "withdraw"; // Send the list of accounts to the "withdraw" view
    }

    @PostMapping("/withdraw")
    public String withdraw(@RequestParam("accountNumber") int accountNumber,
                           @RequestParam("amount") double amount,
                           Model model) {
        BankAccount account = database.getAccountDetails(accountNumber);
        if (account != null) {
            if (account.withdraw(amount)) {
                database.modifyAccount(account.getAccountNumber(), account.getAccountHolder(), account.getBalance());
                model.addAttribute("message", "Withdrawal successful! New Balance: " + account.getBalance());
            } else {
                model.addAttribute("message", "Insufficient funds.");
            }
        } else {
            model.addAttribute("message", "Account not found.");
        }
        return "status";
    }

    @GetMapping("/transfer")
    public String transferForm(Model model) {
        List<BankAccount> accounts = database.getAllAccounts();
        model.addAttribute("accounts", accounts); // Send accounts to be used for both sender and receiver
        return "transfer"; // Send the list of accounts to the "transfer" view
    }

    @PostMapping("/transfer")
    public String transfer(@RequestParam("fromAccount") int fromAccount,
                           @RequestParam("toAccount") int toAccount,
                           @RequestParam("amount") double amount,
                           Model model) {
        BankAccount sender = database.getAccountDetails(fromAccount);
        BankAccount receiver = database.getAccountDetails(toAccount);
        if (sender != null && receiver != null) {
            if (sender.withdraw(amount)) {
                receiver.deposit(amount);
                database.modifyAccount(sender.getAccountNumber(), sender.getAccountHolder(), sender.getBalance());
                database.modifyAccount(receiver.getAccountNumber(), receiver.getAccountHolder(), receiver.getBalance());
                model.addAttribute("message", "Transfer successful!");
            } else {
                model.addAttribute("message", "Insufficient funds in sender's account.");
            }
        } else {
            model.addAttribute("message", "One or both accounts not found.");
        }
        return "status";
    }

    @GetMapping("/details")
    public String accountDetailsForm() {
        return "details"; // Form to view account details
    }

    @PostMapping("/details")
    public String accountDetails(
            @RequestParam("accountNumber") int accountNumber,
            Model model) {

        BankAccount account = database.getAccountDetails(accountNumber);
        if (account != null) {
            model.addAttribute("account", account);
            return "account-details";
        } else {
            model.addAttribute("message", "Account not found.");
            return "status";
        }
    }
}
