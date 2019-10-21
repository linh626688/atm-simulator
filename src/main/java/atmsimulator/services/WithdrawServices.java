package atmsimulator.services;

public interface WithdrawServices {

    boolean calculateWithdrawAmount(String accountNumber, String pin, int amount);

}
