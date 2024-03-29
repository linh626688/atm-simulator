package atmsimulator.model;

import java.util.ArrayList;
import java.util.List;

public class Account {

    private String name;
    private String pin;
    private int balance;
    private String accountNumber;


    public Account(String name, String pin, int balance, String accountNumber) {
		this.name = name;
		this.pin = pin;
		this.balance = balance;
        this.accountNumber = accountNumber;
    }

    private Account() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
		this.name = name;
    }

    public String getPIN() {
        return pin;
    }

    public void setPIN(String pin) {
        this.pin = pin;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }


}
