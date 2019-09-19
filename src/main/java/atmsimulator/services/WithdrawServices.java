package atmsimulator.services;

public interface WithdrawServices {

    public String calculateWithdrawAmount(String accountNumber, String pin, int amount);

}
