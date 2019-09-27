package atmsimulator.services;

import atmsimulator.model.Account;

import java.util.Optional;

public interface UserServices {

	public Account validateLogin(String accountNumber, String pin);

	public Optional<Account> getAccountByAccountNumber(String accountNumber);
}
