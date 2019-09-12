package atmsimulator.services.impl;

import atmsimulator.model.Account;
import atmsimulator.screen.BaseScreen;
import atmsimulator.screen.TransactionScreen;
import atmsimulator.screen.WelcomeScreen;
import atmsimulator.services.UserServices;

import static atmsimulator.Constant.REGEX_MATCH_NUMBER;
import static atmsimulator.MainApp.users;

public class UserServicesImpl implements UserServices {

    @Override
    public void validate(String accountNumber, String pin) {
        boolean isValid = false;
        for (Account user : users) {
            if (accountNumber.equals(user.getAccountNumber()) && pin.equals(user.getPIN())) {
                WelcomeScreen.balance = user.getBalance();
                TransactionScreen transactionScreen = new TransactionScreen();
                isValid = true;
                transactionScreen.show();
            }
        }
        if (!isValid) {
            System.out.println("Invalid Account Number or PIN");
        }
    }

    @Override
    public void validateAccountNumber(String accountNumber, BaseScreen welcomeScreen) {
        if (accountNumber.length() != 6) {
            System.out.println("Account Number should have 6 digits length");
            welcomeScreen.show();
        }
        if (!accountNumber.matches(REGEX_MATCH_NUMBER)) {
            System.out.println("Account Number should only contains numbers");
            welcomeScreen.show();
        }
    }

    @Override
    public void validatePinNumber(String pin, BaseScreen welcomeScreen) {
        if (pin.length() != 6) {
            System.out.println("PIN should have 6 digits length");
            welcomeScreen.show();
        }
        if (!(pin.matches(REGEX_MATCH_NUMBER))) {
            System.out.println("PIN should only contains numbers");
            welcomeScreen.show();
        }
    }
}
