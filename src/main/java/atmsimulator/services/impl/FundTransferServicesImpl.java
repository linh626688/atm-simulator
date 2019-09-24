package atmsimulator.services.impl;

import atmsimulator.model.Account;
import atmsimulator.model.Transaction;
import atmsimulator.repository.AccountRepository;
import atmsimulator.repository.TransactionRepository;
import atmsimulator.services.FundTransferServices;
import atmsimulator.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;

import static atmsimulator.Constant.*;

@Service
public class FundTransferServicesImpl implements FundTransferServices {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public String submitFundTransaction(String accountNumber, String pin, String destination, int amount, String ref) {
        //validate destination
        Account currentAccount = accountRepository.findAccountByAccountNumberAndPin(accountNumber, pin);
        Account destinationAccount = accountRepository.findAccountByAccountNumber(destination);

        if (currentAccount == null || destinationAccount == null) {
            return "Account is invalid";
        }
        if (currentAccount.getBalance() <= amount) {
            return "Insufficient balance $" + amount;
        }
        if (amount > 1000) {
            return "Maximum amount to transfer is $1000";
        }

        currentAccount.setBalance(currentAccount.getBalance() - amount);
        destinationAccount.setBalance(destinationAccount.getBalance() + amount);
        accountRepository.save(Arrays.asList(currentAccount, destinationAccount));
        Transaction transaction = new Transaction();
        transaction.setAccountNumber(accountNumber);
        transaction.setAmount(String.valueOf(amount));
        transaction.setRef(ref);
        transaction.setTime(LocalDateTime.now());
        transaction.setType(TRANSACTION_FUND_TRANSFER);
        transactionRepository.save(transaction);
        return SUCCESS;
    }
}
