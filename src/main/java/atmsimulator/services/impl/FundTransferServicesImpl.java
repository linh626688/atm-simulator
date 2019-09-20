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

import static atmsimulator.Constant.TRANSACTION_FUND_TRANSFER;
import static atmsimulator.Constant.TRANSACTION_WITHDRAW;

@Service
public class FundTransferServicesImpl implements FundTransferServices {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public boolean submitFundTransaction(String accountNumber, String pin, String destination, int amount) throws Exception {
        //validate destination
        Account currentAccount = accountRepository.findAccountByAccountNumberAndPin(accountNumber, pin);
        Account destinationAccount = accountRepository.findAccountByAccountNumber(destination);

        if (currentAccount == null || destinationAccount == null) {
            throw new Exception("Account is invalid");
        }
        if (currentAccount.getBalance() <= amount) {
            throw new Exception("Insufficient balance $" + amount);
        }
        if (currentAccount.getBalance() > 1000) {
            throw new Exception("Maximum amount to withdraw is $1000");
        }
        //validate balance

        //transfer

        currentAccount.setBalance(currentAccount.getBalance() - amount);
        destinationAccount.setBalance(destinationAccount.getBalance() + amount);
        accountRepository.save(Arrays.asList(currentAccount, destinationAccount));
        Transaction transaction = new Transaction();
        transaction.setAccountNumber(accountNumber);
        transaction.setAmount(String.valueOf(amount));
        transaction.setRef(String.valueOf(Utils.generateRandomRef()));
        transaction.setTime(LocalDateTime.now());
        transaction.setType(TRANSACTION_FUND_TRANSFER);
        transactionRepository.save(transaction);
        return true;
    }
}
