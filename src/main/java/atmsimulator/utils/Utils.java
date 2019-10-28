package atmsimulator.utils;

import atmsimulator.model.Account;
import atmsimulator.model.Transaction;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.nio.file.Paths;

import static atmsimulator.Constant.REGEX_MATCH_NUMBER;

public class Utils {
    private static final Random generator = new Random();

    public static List<Transaction> getTransactionFromCSV() {
        String fileName = "./transaction.csv";
        List<String> listTransactions = new ArrayList<>();
        List<Transaction> listObjTransactions = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            listTransactions = stream
                    .filter((line) -> !line.startsWith("Account Number,Time,Amount,Type,Ref"))
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        listTransactions.forEach(trans -> {
            String[] infoUser = trans.split(",");
            Transaction transObj = new Transaction();
            transObj.setAccountNumber(infoUser[0]);
            transObj.setTime(LocalDateTime.parse(infoUser[1], dateTimeFormat));
            transObj.setAmount(infoUser[2]);
            transObj.setType(infoUser[3]);
            transObj.setRef(infoUser[4]);
            listObjTransactions.add(transObj);
        });
        return listObjTransactions;
    }

    public static List<Account> importAccountFromCSV(InputStream is) {
        List<String> list = new ArrayList<>();
        List<Account> result = new ArrayList<>();
        Path path = null;
        try {
            Path temp = Files.createTempFile("", "");
            Files.copy(is, temp, StandardCopyOption.REPLACE_EXISTING);
            Stream<String> lines = Files.lines(temp);
            list = lines.filter((line) -> !line.startsWith("Name"))
                    .collect(Collectors.toList());
            lines.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        list.forEach(user -> {
            String[] infoUser = user.split(",");
            Account account = new Account();
            account.setName(infoUser[0]);
            account.setPin(infoUser[1]);
            account.setBalance(Integer.parseInt(infoUser[2]));
            account.setAccountNumber(infoUser[3]);
            result.add(account);
        });
        return result;
    }

    public static DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    public static int generateRandomRef() {
        return 100000 + generator.nextInt(900000);
    }

    public static String validateAndCalculateWithdrawAmount(int balance, String amount) {
        if (!amount.matches(REGEX_MATCH_NUMBER)) {
            return "Only Number Allowed";
        } else if (Integer.parseInt(amount) % 10 != 0) {
            return "Invalid amount";

        } else if (Integer.parseInt(amount) > 1000) {
            return "Maximum amount to withdraw is $1000";

        } else if (balance - Integer.parseInt(amount) < 0) {
            return "Insufficient balance $" + amount;
        }
        return null;
    }
}
