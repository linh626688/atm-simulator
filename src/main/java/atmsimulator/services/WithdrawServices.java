package atmsimulator.services;

import atmsimulator.screen.BaseScreen;

public interface WithdrawServices {

    public void calculateWithdrawAmount(String accountNumber, String pin, BaseScreen summaryScreen, BaseScreen transactionScreen, int amount);

    public void validateAndCalculateWithdrawAmount(String amount, BaseScreen otherWithdrawScreen, BaseScreen summaryScreen);

}
