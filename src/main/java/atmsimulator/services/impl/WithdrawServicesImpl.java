package atmsimulator.services.impl;

import atmsimulator.model.Account;
import atmsimulator.model.Transaction;
import atmsimulator.repository.AccountRepository;
import atmsimulator.repository.TransactionRepository;
import atmsimulator.services.TransactionServices;
import atmsimulator.services.WithdrawServices;
import atmsimulator.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;


import static atmsimulator.Constant.*;


public class WithdrawServicesImpl implements WithdrawServices {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    TransactionServices transactionServices;

    @Override
    public String calculateWithdrawAmount(String accountNumber, String pin, int amount) {
        Account account = accountRepository.findAccountByAccountNumberAndPin(accountNumber, pin);
        if (account != null) {
            if (account.getBalance() >= amount) {
                account.setBalance(account.getBalance() - amount);
                Transaction transaction = new Transaction();
                transaction.setAccountNumber(accountNumber);
                transaction.setAmount(String.valueOf(amount));
                transaction.setRef(String.valueOf(Utils.generateRandomRef()));
                transaction.setTime(LocalDateTime.now());
                transaction.setType(TRANSACTION_WITHDRAW);
                transactionRepository.save(transaction);
                accountRepository.save(account);
                return SUMMARY_SCREEN;
            } else if (account.getBalance() < amount) {
                return TRANSACTION_SCREEN;
            }
        }
        return null;
    }
}
