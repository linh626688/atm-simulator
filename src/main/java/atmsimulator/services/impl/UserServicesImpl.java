package atmsimulator.services.impl;

import atmsimulator.DAO.AccountDAO;
import atmsimulator.DAO.impl.AccountDAOImpl;
import atmsimulator.model.Account;
import atmsimulator.screen.WelcomeScreen;
import atmsimulator.services.UserServices;

public class UserServicesImpl implements UserServices {
    private AccountDAO accountDAO = new AccountDAOImpl();

    @Override
    public boolean validateUser(String accountNumber, String pin) {
        boolean isValid = false;
        Account user = accountDAO.findUserByAccountNumberAndPin(accountNumber, pin);
        if (user != null) {
            WelcomeScreen.balance = user.getBalance();
            isValid = true;
        }
        if (!isValid) {
            System.out.println("Invalid Account Number or PIN");
        }
        return isValid;
    }
}
