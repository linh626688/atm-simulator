package atmsimulator;

import atmsimulator.model.Account;
import atmsimulator.repository.AccountRepository;
import atmsimulator.repository.TransactionRepository;
import atmsimulator.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import java.io.InputStream;

@EntityScan(
        basePackageClasses = {SpringBootWebApplication.class, Jsr310JpaConverters.class}
)
@SpringBootApplication
public class SpringBootWebApplication extends SpringBootServletInitializer implements CommandLineRunner {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootWebApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringBootWebApplication.class, args);


    }

    @Override
    public void run(String... args) throws Exception {
//        init account to DB
//        InputStream is = this.getClass().getResourceAsStream("/input_account.csv");
//        accountRepository.save(Utils.importAccountFromCSV(is));

//        init transaction log to DB
//        transactionRepository.save(Utils.getTransactionFromCSV());
    }
}
