package atmsimulator.services;

import atmsimulator.model.Account;

import java.util.Optional;

public interface UserServices {

    Account validateLogin(String accountNumber, String pin);

    Optional<Account> getAccountByAccountNumber(String accountNumber);

    Account createNewAccount(Account account);
}
