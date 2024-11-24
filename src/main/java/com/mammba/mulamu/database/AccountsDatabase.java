package com.mammba.mulamu.database;


import com.mammba.mulamu.model.BankAccount;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountsDatabase {

    public AccountsDatabase(){
        try (Connection connection = connect()) {
            Statement statement = connection.createStatement();
            statement.execute(
                    "CREATE TABLE IF NOT EXISTS Accounts (accountNumber INTEGER PRIMARY KEY, accountHolder TEXT, balance REAL)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection connect() throws SQLException {
        String DATABASE_URL = "jdbc:sqlite:mulamu.db";
        return DriverManager.getConnection(DATABASE_URL);
    }

    public void addAccount(int accountNumber, String accountHolder, double balance) {
        String query = "INSERT INTO Accounts (accountNumber, accountHolder, balance) VALUES (?, ?, ?)";
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, accountNumber);
            statement.setString(2, accountHolder);
            statement.setDouble(3, balance);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public BankAccount getAccountDetails(int accountNumber) {
        String query = "SELECT * FROM Accounts WHERE accountNumber = ?";
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, accountNumber);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int accountNum = resultSet.getInt("accountNumber");
                    String holder = resultSet.getString("accountHolder");
                    double balance = resultSet.getDouble("balance");
                    return new BankAccount(accountNum, holder, balance);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void modifyAccount(int accountNumber, String accountHolder, double balance) {
        String query = "UPDATE Accounts SET accountHolder = ?, balance = ? WHERE accountNumber = ?";
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, accountHolder);
            statement.setDouble(2, balance);
            statement.setInt(3, accountNumber);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<BankAccount> getAllAccounts() {
        List<BankAccount> accounts = new ArrayList<>();
        String query = "SELECT * FROM Accounts";
        try (Connection connection = connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int accountNum = resultSet.getInt("accountNumber");
                String holder = resultSet.getString("accountHolder");
                double balance = resultSet.getDouble("balance");
                accounts.add(new BankAccount(accountNum, holder, balance));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }
}
