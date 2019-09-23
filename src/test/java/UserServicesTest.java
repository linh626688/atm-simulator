import atmsimulator.model.Account;
import atmsimulator.services.UserServices;
import atmsimulator.services.impl.UserServicesImpl;
import org.testng.annotations.Test;

import static atmsimulator.MainApp.users;
import static org.testng.Assert.*;

public class UserServicesTest {

    private UserServices userServices = new UserServicesImpl();

    @Test
    public void verifyUserMethod() {
        Account account = users.get(0);
        assertEquals(account, userServices.verifyUser("112233", "012108"));
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
