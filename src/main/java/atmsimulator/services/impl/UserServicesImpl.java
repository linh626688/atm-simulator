package atmsimulator.services.impl;

import atmsimulator.model.Account;
import atmsimulator.repository.AccountRepository;
import atmsimulator.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public Account validateLogin(String accountNumber, String pin) {
        return accountRepository.findAccountByAccountNumberAndPin(accountNumber, pin);
    }
}
