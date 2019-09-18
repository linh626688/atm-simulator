package atmsimulator.services.impl;

import atmsimulator.DAO.AccountDAO;
import atmsimulator.DAO.impl.AccountDAOImpl;
import atmsimulator.model.Account;
import atmsimulator.model.Transaction;
import atmsimulator.screen.WelcomeScreen;
import atmsimulator.screen.WithdrawScreen;
import atmsimulator.services.TransactionServices;
import atmsimulator.services.UserServices;
import atmsimulator.services.WithdrawServices;
import atmsimulator.utils.Utils;

import java.time.LocalDateTime;


import static atmsimulator.Constant.*;


public class WithdrawServicesImpl implements WithdrawServices {
    private TransactionServices transactionServices = new TransactionServicesImpl();

    private AccountDAO accountDAO = new AccountDAOImpl();

    @Override
    public String calculateWithdrawAmount(String accountNumber, String pin, int amount) {
        Account user = accountDAO.findUserByAccountNumberAndPin(accountNumber, pin);
        if (user != null) {
            if (user.getBalance() >= amount) {
                user.setBalance(user.getBalance() - amount);
                WelcomeScreen.balance = user.getBalance();
                WithdrawScreen.withdrawAmount = "$" + amount;
                Transaction transaction = new Transaction();
                transaction.setAccountNumber(accountNumber);
                transaction.setAmount(String.valueOf(amount));
                transaction.setRef(String.valueOf(Utils.generateRandomRef()));
                transaction.setTime(Utils.dateTimeFormat.format(LocalDateTime.now()));
                transaction.setType(TRANSACTION_WITHDRAW);
                transactionServices.addTransaction(transaction);
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
            Account user = accountDAO.findUserByAccountNumberAndPin(WelcomeScreen.accNumberStatic, WelcomeScreen.pinStatic);
            if (user != null) {
                user.setBalance(WelcomeScreen.balance - Integer.parseInt(amount));
                WelcomeScreen.balance -= Integer.parseInt(amount);
                WithdrawScreen.withdrawAmount = "$" + amount;
                Transaction transaction = new Transaction();
                transaction.setAccountNumber(WelcomeScreen.accNumberStatic);
                transaction.setAmount(amount);
                transaction.setRef(String.valueOf(Utils.generateRandomRef()));
                transaction.setTime(Utils.dateTimeFormat.format(LocalDateTime.now()));
                transaction.setType(TRANSACTION_WITHDRAW);
                transactionServices.addTransaction(transaction);
                return SUMMARY_SCREEN;
            }
        }
        return null;
    }
}
