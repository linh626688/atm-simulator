package atmsimulator.services;

import atmsimulator.model.Account;
import atmsimulator.screen.BaseScreen;


public interface UserServices {

	public boolean validate(String accountNumber, String pin);

	public boolean validateAccountNumber(String accountNumber);

	public boolean validatePinNumber(String pin);

	public Account verifyUser(String accountNumber, String pin);

}
