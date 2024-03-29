import atmsimulator.services.WithdrawServices;
import atmsimulator.services.impl.WithdrawServicesImpl;
import org.testng.annotations.Test;

import static atmsimulator.Constant.*;
import static org.testng.Assert.*;

public class WithdrawServicesTest {
    private WithdrawServices withdrawServices = new WithdrawServicesImpl();

    @Test
    public void calculateWithdrawAmountMethod() {
        assertEquals(TRANSACTION_SCREEN, withdrawServices.calculateWithdrawAmount("112233", "012108", 10));
    }

    @Test
    public void validateAndCalculateWithdrawAmountMethod() {
        assertEquals(OTHER_WITHDRAW_SCREEN, withdrawServices.validateAndCalculateWithdrawAmount("1000"));
    }

}
