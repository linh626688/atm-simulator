package atmsimulator.services.impl;

import atmsimulator.model.Account;
import atmsimulator.screen.*;
import atmsimulator.services.FundTransferServices;

import static atmsimulator.MainApp.users;

public class FundTransferServicesImpl implements FundTransferServices {

    @Override
    public void transaction() {
        Account user = users.stream()
                .filter(account -> account.getAccountNumber().equals(WelcomeScreen.accNumberStatic) && account.getPIN().equals(WelcomeScreen.pinStatic))
                .findAny().orElse(null);
        if (user != null) {
            user.setBalance(WelcomeScreen.balance);
            System.out.println("Transaction Succeed");
        }
        TransactionScreen transactionScreen = new TransactionScreen();
        transactionScreen.show();
    }
}
