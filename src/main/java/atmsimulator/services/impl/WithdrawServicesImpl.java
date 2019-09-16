package atmsimulator.services.impl;

import atmsimulator.model.Account;
import atmsimulator.screen.WelcomeScreen;
import atmsimulator.screen.WithdrawScreen;
import atmsimulator.services.UserServices;
import atmsimulator.services.WithdrawServices;

import static atmsimulator.Constant.*;


public class WithdrawServicesImpl implements WithdrawServices {
    private UserServices userServices = new UserServicesImpl();

    @Override
    public String calculateWithdrawAmount(String accountNumber, String pin, int amount) {
        Account user = userServices.verifyUser(accountNumber, pin);
        if (user != null) {
            if (user.getBalance() >= amount) {
                user.setBalance(user.getBalance() - amount);
                WelcomeScreen.balance = user.getBalance();
                WithdrawScreen.withdrawAmount = "$" + amount;
                return SUMMARY_SCREEN;
            } else if (user.getBalance() < amount) {
                System.out.println("Insufficient balance $" + amount);
                return TRANSACTION_SCREEN;
            }
        }
        return null;
    }

    @Override
    public String validateAndCalculateWithdrawAmount(String amount) {
        if (!amount.matches(REGEX_MATCH_NUMBER)) {
            System.out.println("Only Number Allowed");
            return OTHER_WITHDRAW_SCREEN;
        } else if (Integer.parseInt(amount) % 10 != 0) {
            System.out.println("Invalid amount");
            return OTHER_WITHDRAW_SCREEN;

        } else if (Integer.parseInt(amount) > 1000) {
            System.out.println("Maximum amount to withdraw is $1000");
            return OTHER_WITHDRAW_SCREEN;

        } else if (WelcomeScreen.balance - Integer.parseInt(amount) < 0) {
            System.out.println("Insufficient balance $" + amount);
            return OTHER_WITHDRAW_SCREEN;

        } else {
            Account user = userServices.verifyUser(WelcomeScreen.accNumberStatic, WelcomeScreen.pinStatic);
            if (user != null) {
                user.setBalance(WelcomeScreen.balance - Integer.parseInt(amount));
                WelcomeScreen.balance -= Integer.parseInt(amount);
                WithdrawScreen.withdrawAmount = "$" + amount;
                return SUMMARY_SCREEN;
            }
        }
        return null;
    }
}
