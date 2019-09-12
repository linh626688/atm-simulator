import atmsimulator.services.UserServices;
import atmsimulator.services.impl.UserServicesImpl;
import org.testng.annotations.Test;

public class UserServicesTest {

    UserServices userServices = new UserServicesImpl();

    @Test
    public void validateTest() {
        userServices.validate("112233","112233");
    }
}
