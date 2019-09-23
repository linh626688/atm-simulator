import atmsimulator.services.FundTransferServices;
import atmsimulator.services.impl.FundTransferServicesImpl;
import org.testng.annotations.Test;

import static atmsimulator.MainApp.users;
import static org.testng.Assert.*;

public class FundTransferServicesTest {
    private FundTransferServices fundTransferServices = new FundTransferServicesImpl();

    @Test
    public void validateTransactionMethod() {
        assertTrue(fundTransferServices.validateTransaction("112233", "012108"));
    }
}
