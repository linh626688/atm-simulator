package atmsimulator;

import atmsimulator.DAO.AccountDAO;
import atmsimulator.DAO.impl.AccountDAOImpl;
import atmsimulator.model.Account;
import atmsimulator.screen.*;

import java.util.ArrayList;
import java.util.List;

public class MainApp {

    public static List<Account> users = new ArrayList<>();
    public static WelcomeScreen welcomeScreen = new WelcomeScreen();
    public static TransactionScreen transactionScreen = new TransactionScreen();
    public static SummaryScreen summaryScreen = new SummaryScreen();
    public static OtherWithdrawScreen otherWithdrawScreen = new OtherWithdrawScreen();
    public static WithdrawScreen withdrawScreen = new WithdrawScreen();
    public static FundTransferScreen fundTransferScreen = new FundTransferScreen();
    public static FundTransferSummaryScreen fundTransferSummaryScreen = new FundTransferSummaryScreen();
    public static TransactionLogScreen transactionLogScreen = new TransactionLogScreen();

    public static void main(String[] args) {
        AccountDAO dao = new AccountDAOImpl();
        dao.importAccount();
        for (; ; ) {
            welcomeScreen.show();
        }
    }

}
