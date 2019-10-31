package atmsimulator.config;

import atmsimulator.ATMSimulatorTests;
import atmsimulator.SpringBootWebApplication;
import atmsimulator.model.Account;
import atmsimulator.repository.AccountRepository;
import atmsimulator.services.UserServices;
import cucumber.api.java.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = SpringBootWebApplication.class, loader = SpringBootContextLoader.class)
@ActiveProfiles("test")
@Transactional
public class CucumberContextConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(CucumberContextConfiguration.class);
    @Autowired
    private UserServices userServices;

    /**
     * Need this method so the cucumber will recognize this class as glue and load spring context configuration
     */
    @Before
    public void setUp() {
        logger.info("-------------- Spring Context Initialized For Executing Cucumber Tests --------------");
        Account newAccount = new Account();
        newAccount.setName("Wayne Rooney");
        newAccount.setAccountNumber("112233");
        newAccount.setPin("012108");
        newAccount.setBalance(1000);
        Optional<Account> account = userServices.getAccountByAccountNumber("112233");
        if (account.isPresent()) {
            account.get().setBalance(1000);
        } else {
            userServices.createNewAccount(newAccount);
        }
        Account newAccount2 = new Account();
        newAccount2.setName("Louis Saha");
        newAccount2.setAccountNumber("112244");
        newAccount2.setPin("932012");
        newAccount2.setBalance(1000);
        Optional<Account> account2 = userServices.getAccountByAccountNumber("112244");
        if (!account2.isPresent()) {
            userServices.createNewAccount(newAccount2);
        }
    }

}
