package atmsimulator;

import atmsimulator.model.Account;
import atmsimulator.model.Transaction;
import atmsimulator.repository.AccountRepository;
import atmsimulator.services.FundTransferServices;
import atmsimulator.services.TransactionServices;
import atmsimulator.services.UserServices;
import atmsimulator.services.WithdrawServices;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static atmsimulator.Constant.SUCCESS;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringBootWebApplication.class, TestProfileConfig.class})
public class ATMSimulatorTests {
    @Autowired
    private UserServices userServices;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private FundTransferServices fundTransferServices;

    @Autowired
    private WithdrawServices withdrawServices;

    @Autowired
    private TransactionServices transactionServices;

    @Before
    public void prepareDataForTest() {
        accountRepository.deleteAll();
        initData();
    }

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

    public void initData() {
        Account newAccount = new Account();
        newAccount.setName("Wayne Rooney");
        newAccount.setAccountNumber("112233");
        newAccount.setPin("012108");
        newAccount.setBalance(1000);
        Account newAccount2 = new Account();
        newAccount2.setName("Louis Saha");
        newAccount2.setAccountNumber("112244");
        newAccount2.setPin("932012");
        newAccount2.setBalance(1000);
        accountRepository.save(Arrays.asList(newAccount, newAccount2));
    }
}