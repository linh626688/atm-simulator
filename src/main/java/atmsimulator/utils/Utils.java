package atmsimulator.utils;

import atmsimulator.model.Account;
import atmsimulator.model.Transaction;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static atmsimulator.Constant.CSV_SEPARATOR;

public class Utils {
    private static final Random generator = new Random();

    public static void writeTransactionLog(Transaction transaction) {
        {
            try {
                StringBuffer firstLine = new StringBuffer();
                firstLine.append("Account Number,Time,Amount,Type,Ref\n");
                File tempFile = new File("./transaction.csv");
                boolean isExistsFile = tempFile.exists();
                PrintWriter bw = new PrintWriter(new BufferedWriter(new FileWriter("transaction.csv", true)));
                // header line
                if (!isExistsFile) {
                    bw.write(firstLine.toString());
                }
                // log
                StringBuffer oneLine = new StringBuffer();
                oneLine.append(transaction.getAccountNumber());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(transaction.getTime());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(transaction.getAmount());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(transaction.getType());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(transaction.getRef());
                oneLine.append('\n');
                bw.write(oneLine.toString());

                bw.flush();
                bw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

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

    public static List<Account> importAccountFromCSV() {
        String fileName = "./input_account.csv";
        List<String> list = new ArrayList<>();
        List<Account> result = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            list = stream
                    .filter((line) -> !line.startsWith("Name"))
                    .collect(Collectors.toList());

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
        return  result;
    }

    public static DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm a");

    public static int generateRandomRef() {
        return 100000 + generator.nextInt(900000);
    }

}
