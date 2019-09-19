package atmsimulator.services;

import atmsimulator.model.Account;

public interface UserServices {

	public Account validateLogin(String accountNumber, String pin);
}
