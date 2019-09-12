package atmsimulator.services;

import atmsimulator.screen.BaseScreen;


public interface UserServices {

	public void validate(String accountNumber, String pin);

	public void validateAccountNumber(String accountNumber, BaseScreen welcomeScreen);

	public void validatePinNumber(String pin, BaseScreen welcomeScreen);

}
