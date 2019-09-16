package atmsimulator.screen;


import atmsimulator.services.WithdrawServices;
import atmsimulator.services.impl.WithdrawServicesImpl;

import java.util.Scanner;

import static atmsimulator.Constant.*;
import static atmsimulator.MainApp.*;

public class WithdrawScreen implements BaseScreen {
    public static String withdrawAmount;

    public void show() {

        System.out.println("1. $10");
        System.out.println("2. $50");
        System.out.println("3. $100");
        System.out.println("4. Other");
        System.out.println("5. Back");

        Scanner scan = new Scanner(System.in);
        int opt = scan.nextInt();
        WithdrawServices withdrawServices = new WithdrawServicesImpl();
        String screenShow = null;
        switch (opt) {
            case 1:
                screenShow = withdrawServices.calculateWithdrawAmount(WelcomeScreen.accNumberStatic, WelcomeScreen.pinStatic, 10);
                break;
            case 2:
                screenShow = withdrawServices.calculateWithdrawAmount(WelcomeScreen.accNumberStatic, WelcomeScreen.pinStatic, 50);
                break;
            case 3:
                screenShow = withdrawServices.calculateWithdrawAmount(WelcomeScreen.accNumberStatic, WelcomeScreen.pinStatic, 100);
                break;
            case 4:
                screenShow = OTHER_WITHDRAW_SCREEN;
                break;
            default:
                screenShow = TRANSACTION_SCREEN;
                break;
        }
        if (screenShow != null) {
            switch (screenShow) {
                case OTHER_WITHDRAW_SCREEN:
                    otherWithdrawScreen.show();
                    break;
                case TRANSACTION_SCREEN:
                    transactionScreen.show();
                    break;
                case SUMMARY_SCREEN:
                    summaryScreen.show();
                    break;
                default:
                    break;
            }
        }

    }
}
