package atmsimulator.services;


import atmsimulator.screen.BaseScreen;

public interface FundTransferServices {

    void inputDestinationAccount();

    void inputAmount();

    void inputReferenceNumber();

    void transferConfirm(BaseScreen fundTransferScreen);

    void transaction();

}
