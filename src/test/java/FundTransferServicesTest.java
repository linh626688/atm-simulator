import atmsimulator.DAO.AccountDAO;
import atmsimulator.DAO.impl.AccountDAOImpl;
import atmsimulator.services.FundTransferServices;
import atmsimulator.services.impl.FundTransferServicesImpl;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static atmsimulator.MainApp.users;
import static org.testng.Assert.*;

public class FundTransferServicesTest {
    private FundTransferServices fundTransferServices = new FundTransferServicesImpl();
    private AccountDAO accountDAO = new AccountDAOImpl();

    @BeforeMethod
    public void importAccount() {
        accountDAO.importAccount();
    }

    @Test
    public void validateTransactionMethod() {
        assertTrue(fundTransferServices.validateTransaction("112233", "012108"));
    }
}
