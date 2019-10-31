package atmsimulator.services.impl;

import atmsimulator.model.Account;
import atmsimulator.repository.AccountRepository;
import atmsimulator.services.UserServices;
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
public class UserServicesImpl implements UserServices {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public Account validateLogin(String accountNumber, String pin) {
        return accountRepository.findAccountByAccountNumberAndPin(accountNumber, pin);
    }

    @Override
    public Optional<Account> getAccountByAccountNumber(String accountNumber) {
        return accountRepository.findAccountByAccountNumber(accountNumber);
    }

    @Override
    public Account createNewAccount(Account account) {
        return accountRepository.save(account);
    }
}
