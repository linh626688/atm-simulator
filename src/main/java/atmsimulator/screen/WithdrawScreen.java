package atmsimulator.screen;


import atmsimulator.services.WithdrawServices;
import atmsimulator.services.impl.WithdrawServicesImpl;

import java.util.Scanner;

import static atmsimulator.MainApp.users;

public class WithdrawScreen implements BaseScreen{
    public static String withdrawAmount;

    public void show() {

        System.out.println("1. $10");
        System.out.println("2. $50");
        System.out.println("3. $100");
        System.out.println("4. Other");
        System.out.println("5. Back");

        Scanner scan = new Scanner(System.in);
        int opt = scan.nextInt();
        SummaryScreen summaryScreen = new SummaryScreen();
        TransactionScreen transactionScreen = new TransactionScreen();
        OtherWithdrawScreen otherWithdrawScreen = new OtherWithdrawScreen();
        WithdrawServices withdrawServices = new WithdrawServicesImpl();

        switch (opt) {
            case 1:
                withdrawServices.calculateWithdrawAmount(WelcomeScreen.accNumberStatic, WelcomeScreen.pinStatic, summaryScreen, transactionScreen, 10);
                break;
            case 2:
                withdrawServices.calculateWithdrawAmount(WelcomeScreen.accNumberStatic, WelcomeScreen.pinStatic, summaryScreen, transactionScreen, 50);
                break;
            case 3:
                withdrawServices.calculateWithdrawAmount(WelcomeScreen.accNumberStatic, WelcomeScreen.pinStatic, summaryScreen, transactionScreen, 100);
                break;
            case 4:
                otherWithdrawScreen.show();
                break;
            default:
                transactionScreen.show();
                break;
        }
    }
}
