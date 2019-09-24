package atmsimulator;

import atmsimulator.model.Account;
import atmsimulator.model.Transaction;
import atmsimulator.services.FundTransferServices;
import atmsimulator.services.TransactionServices;
import atmsimulator.services.UserServices;
import atmsimulator.services.WithdrawServices;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static atmsimulator.Constant.SUCCESS;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringBootWebApplication.class})
public class ATMSimulatorTests {
    @Autowired
    private UserServices userServices;

    @Autowired
    private FundTransferServices fundTransferServices;

    @Autowired
    private WithdrawServices withdrawServices;

    @Autowired
    private TransactionServices transactionServices;


    @Test
    public void validateLoginTest() {
        Account account = userServices.validateLogin("112233", "012108");
        Assert.assertEquals(account.getAccountNumber(), "112233");
    }

    @Test
    public void submitFundTransactionTest() {
        String result = fundTransferServices.submitFundTransaction("112233", "012108", "112244", 100, "888888");
        Assert.assertEquals(result, SUCCESS);
    }

    @Test
    public void calculateWithdrawAmountTest() {
        boolean result = withdrawServices.calculateWithdrawAmount("112233", "012108", 40);
        Assert.assertTrue(result);
    }

    @Test
    public void latest10TransactionByAccountTest() {
        List<Transaction> result = transactionServices.latest10TransactionByAccount("112233");
        Assert.assertEquals(result.get(0).getAccountNumber(), "112233");
    }
}