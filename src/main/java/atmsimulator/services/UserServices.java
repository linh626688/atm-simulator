package atmsimulator.services;

import atmsimulator.model.Account;

public interface UserServices {

	Account validateLogin(String accountNumber, String pin);
}
