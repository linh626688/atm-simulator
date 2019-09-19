package atmsimulator.services.impl;

import atmsimulator.model.Account;
import atmsimulator.repository.AccountRepository;
import atmsimulator.services.FundTransferServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class FundTransferServicesImpl implements FundTransferServices {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public boolean submitFundTransaction(String accountNumber, String pin, String destination, int amount) {
        Account currentAccount = accountRepository.findAccountByAccountNumberAndPin(accountNumber, pin);
        Account destinationAccount = accountRepository.findAccountByAccountNumber(destination);
        if (currentAccount != null && destinationAccount != null) {
            currentAccount.setBalance(currentAccount.getBalance() - amount);
            destinationAccount.setBalance(destinationAccount.getBalance() + amount);
            accountRepository.save(Arrays.asList(currentAccount, destinationAccount));
            return true;
        }
        return false;
    }
}
