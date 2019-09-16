package atmsimulator.screen;

import atmsimulator.services.FundTransferServices;
import atmsimulator.services.impl.FundTransferServicesImpl;

import java.util.Scanner;

import static atmsimulator.MainApp.transactionScreen;
import static atmsimulator.MainApp.welcomeScreen;

public class FundTransferSummaryScreen implements BaseScreen {

    public void show() {
        FundTransferServices fundTransferServices = new FundTransferServicesImpl();
        System.out.println("Fund Transfer Summary Screen");
        System.out.println("----------------------");

        System.out.println("Destination Account: " + FundTransferScreen.destinationAcc);
        System.out.println("Transfer Amount: $" + FundTransferScreen.transferAmt);
        System.out.println("Reference Number: " + FundTransferScreen.referenceNum);
        System.out.println("Balance: $" + WelcomeScreen.balance);

        System.out.println("1. Transaction");
        System.out.println("2. Exit");

        Scanner scan = new Scanner(System.in);
        String opt = scan.nextLine();

        switch (opt) {
            case "1":
                if (fundTransferServices.validateTransaction()) {
                    transactionScreen.show();
                }
                break;
            case "2":
                welcomeScreen.show();
                break;
            default:
                show();
                break;
        }
    }
}
