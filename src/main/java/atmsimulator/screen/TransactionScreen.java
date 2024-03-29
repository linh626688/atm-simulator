package atmsimulator.screen;

import java.util.Scanner;

import static atmsimulator.MainApp.*;

public class TransactionScreen implements BaseScreen {
    public void show() {
        System.out.println("Transaction Screen");
        System.out.println("----------------");

        System.out.println("1. Withdraw ");
        System.out.println("2. Fund Transfer");
        System.out.println("3. Exit ");
        System.out.println("Please choose option[3]:");

        Scanner scan = new Scanner(System.in);
        String opt = scan.nextLine();

        switch (opt) {
            case "1":
                withdrawScreen.show();
                break;
            case "2":
                fundTransferScreen.show();
                break;
            default:
                welcomeScreen.show();
                break;
        }

    }
}
