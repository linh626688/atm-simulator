package atmsimulator;

import atmsimulator.model.Account;
import atmsimulator.screen.*;

import java.util.Arrays;
import java.util.List;

public class MainApp {
    public static List<Account> users = Arrays.asList(
            new Account("John Doe", "012108", 100, "112233"),
            new Account("Jane Doe", "932012", 30, "112244"));
    public static WelcomeScreen welcomeScreen = new WelcomeScreen();
    public static TransactionScreen transactionScreen = new TransactionScreen();
    public static SummaryScreen summaryScreen = new SummaryScreen();
    public static OtherWithdrawScreen otherWithdrawScreen = new OtherWithdrawScreen();
    public static WithdrawScreen withdrawScreen = new WithdrawScreen();
    public static FundTransferScreen fundTransferScreen = new FundTransferScreen();
    public static FundTransferSummaryScreen fundTransferSummaryScreen = new FundTransferSummaryScreen();

    public static void main(String[] args) {
        for (;;){
            welcomeScreen.show();
        }
    }
}
