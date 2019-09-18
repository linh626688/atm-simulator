package atmsimulator.screen;

import atmsimulator.model.Transaction;
import atmsimulator.services.TransactionServices;
import atmsimulator.services.impl.TransactionServicesImpl;
import atmsimulator.utils.Utils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import static atmsimulator.MainApp.*;


public class TransactionLogScreen implements BaseScreen {
    private TransactionServices transactionServices = new TransactionServicesImpl();

    @Override
    public void show() {
        System.out.println("Transaction Log - Last 10 transactions");
        System.out.println("----------------");
        System.out.println("Date : " + Utils.dateTimeFormat.format(LocalDateTime.now()));
        System.out.println("Account Number\tTime\t\tAmount\tType\t\t\tRef");
        List<Transaction> listTransactions = transactionServices.latest10TransactionByAccount(WelcomeScreen.accNumberStatic);
        listTransactions.forEach(item -> System.out.println(item.toString()));
        System.out.println("1. Transaction");
        System.out.println("2. Exit");

        Scanner scan = new Scanner(System.in);
        String opt = scan.nextLine();
        switch (opt) {
            case "1":
                transactionScreen.show();
                break;
            default:
                welcomeScreen.show();
                break;
        }
    }
}
