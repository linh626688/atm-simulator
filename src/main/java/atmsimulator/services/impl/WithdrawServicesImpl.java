package atmsimulator.services.impl;

import atmsimulator.model.Account;
import atmsimulator.screen.BaseScreen;
import atmsimulator.screen.WelcomeScreen;
import atmsimulator.screen.WithdrawScreen;
import atmsimulator.services.WithdrawServices;

import static atmsimulator.Constant.REGEX_MATCH_NUMBER;
import static atmsimulator.MainApp.users;


public class WithdrawServicesImpl implements WithdrawServices {
    @Override
    public void calculateWithdrawAmount(String accountNumber, String pin, BaseScreen summaryScreen, BaseScreen transactionScreen, int amount) {
        for (Account user : users) {
            if (WelcomeScreen.accNumberStatic.equals(user.getAccountNumber())
                    && WelcomeScreen.pinStatic.equals(user.getPIN()) && user.getBalance() >= amount) {
                user.setBalance(user.getBalance() - amount);
                WelcomeScreen.balance = user.getBalance();
                WithdrawScreen.withdrawAmount = "$" + amount;
                summaryScreen.show();
            } else if (user.getBalance() < amount) {
                System.out.println("Insufficient balance $" + amount);
                transactionScreen.show();
            }
        }
    }

    @Override
    public void validateAndCalculateWithdrawAmount(String amount, BaseScreen otherWithdrawScreen, BaseScreen summaryScreen) {
        if (!amount.matches(REGEX_MATCH_NUMBER)) {
            System.out.println("Only Number Allowed");
            otherWithdrawScreen.show();
        } else if (Integer.parseInt(amount) % 10 != 0) {
            System.out.println("Invalid amount");
            otherWithdrawScreen.show();
        } else if (Integer.parseInt(amount) > 1000) {
            System.out.println("Maximum amount to withdraw is $1000");
            otherWithdrawScreen.show();
        } else if (WelcomeScreen.balance - Integer.parseInt(amount) < 0) {
            System.out.println("Insufficient balance $" + amount);
            otherWithdrawScreen.show();
        } else {
            users.stream()
                    .filter(user -> user.getAccountNumber().equals(WelcomeScreen.accNumberStatic) && user.getPIN().equals(WelcomeScreen.pinStatic))
                    .findAny()
                    .ifPresent(user -> {
                        user.setBalance(WelcomeScreen.balance - Integer.parseInt(amount));
                        WelcomeScreen.balance -= Integer.parseInt(amount);
                        WithdrawScreen.withdrawAmount = "$" + amount;
                        summaryScreen.show();
                    });
        }
    }
}
