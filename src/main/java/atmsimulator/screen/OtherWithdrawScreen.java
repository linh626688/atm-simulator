package atmsimulator.screen;

import atmsimulator.services.WithdrawServices;
import atmsimulator.services.impl.WithdrawServicesImpl;

import java.util.Scanner;

import static atmsimulator.MainApp.users;

public class OtherWithdrawScreen implements BaseScreen {

    public void show() {
        SummaryScreen summaryScreen = new SummaryScreen();
        OtherWithdrawScreen otherWithdrawScreen = new OtherWithdrawScreen();
        WithdrawServices withdrawServices = new WithdrawServicesImpl();

        System.out.println("Other Withdraw Screen");
        System.out.println("--------------------");
        System.out.print("Enter amount to withdraw: ");
        Scanner scan = new Scanner(System.in);
        String amount = scan.nextLine();

        withdrawServices.validateAndCalculateWithdrawAmount(amount, otherWithdrawScreen, summaryScreen);
    }
}