package atmsimulator.services.impl;

import atmsimulator.model.Account;
import atmsimulator.model.Transaction;
import atmsimulator.repository.AccountRepository;
import atmsimulator.repository.TransactionRepository;
import atmsimulator.services.TransactionServices;
import atmsimulator.services.WithdrawServices;
import atmsimulator.utils.Utils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


import static atmsimulator.Constant.*;

@Service
@Transactional
public class WithdrawServicesImpl implements WithdrawServices {
    Logger logger = LogManager.getLogger(WithdrawServicesImpl.class);

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    TransactionServices transactionServices;

    @Override
    public boolean calculateWithdrawAmount(String accountNumber, String pin, int amount) {
        Account account = accountRepository.findAccountByAccountNumberAndPin(accountNumber, pin);
        if (account != null) {
            if (account.getBalance() >= amount) {
                account.setBalance(account.getBalance() - amount);
                Transaction transaction = new Transaction();
                transaction.setAccountNumber(accountNumber);
                transaction.setAmount(String.valueOf(amount));
                String ref = String.valueOf(Utils.generateRandomRef());
                transaction.setRef(ref);
                transaction.setTime(LocalDateTime.now());
                transaction.setType(TRANSACTION_WITHDRAW);
                try {
                    transactionRepository.save(transaction);
                    logger.info("update info account - account: " + account.getAccountNumber());

                    accountRepository.save(account);
                    logger.info("insert new transaction - ref: " + ref);

                } catch (Exception ex) {
                    logger.error(ex.getMessage());
                }
                return true;
            }
        }
        return false;
    }
}
