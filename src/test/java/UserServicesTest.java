import atmsimulator.DAO.AccountDAO;
import atmsimulator.DAO.impl.AccountDAOImpl;
import atmsimulator.services.UserServices;
import atmsimulator.services.impl.UserServicesImpl;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UserServicesTest {

    private UserServices userServices = new UserServicesImpl();
    private AccountDAO accountDAO = new AccountDAOImpl();

    @BeforeMethod
    public void importAccount(){
        accountDAO.importAccount();
    }

    @Test
    public void verifyUserMethod() {
        assertTrue(userServices.validateUser("112233", "012108"));
    }

    @Test
    public void validateUserExistMethod() {
        assertTrue(userServices.validateUser("112233", "012108"));
    }

    @Test
    public void validateUserNotExistMethod() {
        assertFalse(userServices.validateUser("112244", "012108"));
    }

}
