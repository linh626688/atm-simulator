package atmsimulator.screen;


import atmsimulator.services.FundTransferServices;
import atmsimulator.services.impl.FundTransferServicesImpl;

public class FundTransferScreen implements BaseScreen {
    public static String destinationAcc;
    public static String transferAmt;
    public static String referenceNum;

    FundTransferServices fundTransferServices = new FundTransferServicesImpl();

    public void show() {

        System.out.println("Transfer Screen");
        System.out.println("----------------");
        fundTransferServices.inputDestinationAccount();

    }

}
