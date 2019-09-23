package atmsimulator.utils;

import atmsimulator.model.Transaction;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.Random;

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

    public static int generateRandomRef() {
        return 100000 + generator.nextInt(900000);
    }

    public static DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm a");

}
