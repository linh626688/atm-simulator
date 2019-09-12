package atmsimulator.screen;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class SummaryScreen implements BaseScreen {

    public void show() {

        TransactionScreen ts = new TransactionScreen();
        WelcomeScreen ws = new WelcomeScreen();

        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Scanner scan = new Scanner(System.in);

        System.out.println("Summary Screen");
        System.out.println("----------------");
        System.out.println("Date : " + dateTimeFormat.format(now));
        System.out.println("Withdraw: " + WithdrawScreen.withdrawAmount);
        System.out.println("Balance: $" + WelcomeScreen.balance);

        System.out.println("1. Transaction");
        System.out.println("2. Exit");

        int opt = scan.nextInt();
        switch (opt) {
            case 1:
                ts.show();
                break;
            case 2:
                ws.show();
                break;
            default:
                System.out.println("default");
                break;
        }
    }
}
