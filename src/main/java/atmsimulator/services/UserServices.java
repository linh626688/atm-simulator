package atmsimulator.services;

import atmsimulator.model.Account;


public interface UserServices {

	public boolean validateUser(String accountNumber, String pin);

	public Account verifyUser(String accountNumber, String pin);

}
