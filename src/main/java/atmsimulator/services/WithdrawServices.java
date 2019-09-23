package atmsimulator.services;

public interface WithdrawServices {

    public boolean calculateWithdrawAmount(String accountNumber, String pin, int amount);

}
