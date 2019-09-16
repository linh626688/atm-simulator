package atmsimulator.screen;

import atmsimulator.services.WithdrawServices;
import atmsimulator.services.impl.WithdrawServicesImpl;

import java.util.Scanner;

import static atmsimulator.Constant.OTHER_WITHDRAW_SCREEN;
import static atmsimulator.Constant.SUMMARY_SCREEN;
import static atmsimulator.MainApp.otherWithdrawScreen;
import static atmsimulator.MainApp.summaryScreen;

public class OtherWithdrawScreen implements BaseScreen {

    public void show() {
        WithdrawServices withdrawServices = new WithdrawServicesImpl();

        System.out.println("Other Withdraw Screen");
        System.out.println("--------------------");
        System.out.print("Enter amount to withdraw: ");
        Scanner scan = new Scanner(System.in);
        String amount = scan.nextLine();

        String screenShow = withdrawServices.validateAndCalculateWithdrawAmount(amount);
        switch (screenShow) {
            case OTHER_WITHDRAW_SCREEN:
                otherWithdrawScreen.show();
                break;
            case SUMMARY_SCREEN:
                summaryScreen.show();
                break;
            default:
                break;
        }
    }
}