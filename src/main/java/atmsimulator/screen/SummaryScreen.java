package atmsimulator.screen;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static atmsimulator.MainApp.transactionScreen;
import static atmsimulator.MainApp.welcomeScreen;

public class SummaryScreen implements BaseScreen {

    public void show() {
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm a");
        LocalDateTime now = LocalDateTime.now();
        Scanner scan = new Scanner(System.in);

        System.out.println("Summary Screen");
        System.out.println("----------------");
        System.out.println("Date : " + dateTimeFormat.format(now));
        System.out.println("Withdraw: " + WithdrawScreen.withdrawAmount);
        System.out.println("Balance: $" + WelcomeScreen.balance);

        System.out.println("1. Transaction");
        System.out.println("2. Exit");
        System.out.println("Choose option[2]:");

        int opt = scan.nextInt();
        switch (opt) {
            case 1:
                transactionScreen.show();
                break;
            case 2:
                welcomeScreen.show();
                break;
            default:
                welcomeScreen.show();
                break;
        }
    }
}
