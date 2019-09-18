package atmsimulator.services.impl;

import atmsimulator.DAO.AccountDAO;
import atmsimulator.DAO.impl.AccountDAOImpl;
import atmsimulator.model.Account;
import atmsimulator.screen.WelcomeScreen;
import atmsimulator.services.UserServices;

import static atmsimulator.Constant.NUMBER_LENGTH;
import static atmsimulator.Constant.REGEX_MATCH_NUMBER;
import static atmsimulator.MainApp.users;

public class UserServicesImpl implements UserServices {
    private AccountDAO accountDAO = new AccountDAOImpl();

    @Override
    public boolean validate(String accountNumber, String pin) {
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


    @Override
    public boolean validateAccountNumber(String accountNumber) {
        if (accountNumber.length() != NUMBER_LENGTH) {
            System.out.println("Account Number should have 6 digits length");
            return false;
        }
        if (!accountNumber.matches(REGEX_MATCH_NUMBER)) {
            System.out.println("Account Number should only contains numbers");
        }
        return true;
    }

    @Override
    public boolean validatePinNumber(String pin) {
        if (pin.length() != NUMBER_LENGTH) {
            System.out.println("PIN should have 6 digits length");
            return false;
        }
        if (!(pin.matches(REGEX_MATCH_NUMBER))) {
            System.out.println("PIN should only contains numbers");
            return false;
        }
        return true;
    }

}
