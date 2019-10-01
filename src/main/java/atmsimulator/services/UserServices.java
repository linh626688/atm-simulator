package atmsimulator.services;

public interface UserServices {

    public boolean validateUser(String accountNumber, String pin);

    public boolean validateUserByAccountNumber(String accountNumber);

}
