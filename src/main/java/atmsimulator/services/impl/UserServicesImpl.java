package atmsimulator.services.impl;

import atmsimulator.model.Account;
import atmsimulator.screen.WelcomeScreen;
import atmsimulator.services.UserServices;

import static atmsimulator.MainApp.users;

public class UserServicesImpl implements UserServices {

    @Override
    public boolean validateUser(String accountNumber, String pin) {
        boolean isValid = false;
        Account user = verifyUser(accountNumber, pin);
        if (user != null) {
            WelcomeScreen.balance = user.getBalance();
            isValid = true;
        }
        if (!isValid) {
            System.out.println("Invalid Account Number or PIN");
        }
        return isValid;
    }


    @Override
    public Account verifyUser(String accountNumber, String pin) {
        return users.stream()
                .filter(user -> accountNumber.equals(user.getAccountNumber()) && pin.equals(user.getPIN()))
                .findAny()
                .orElse(null);
    }
}
