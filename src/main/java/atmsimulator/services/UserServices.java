package atmsimulator.services;

import atmsimulator.model.Account;


public interface UserServices {

    public boolean validateUser(String accountNumber, String pin);

	public boolean validateAccountNumber(String accountNumber);

	public boolean validatePinNumber(String pin);
}
