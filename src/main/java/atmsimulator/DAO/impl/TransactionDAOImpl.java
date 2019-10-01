package atmsimulator.DAO.impl;

import atmsimulator.DAO.TransactionDAO;
import atmsimulator.model.Transaction;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static atmsimulator.Constant.CSV_SEPARATOR;

public class TransactionDAOImpl implements TransactionDAO {
    @Override
    public void addTransaction(Transaction transaction) {
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

    @Override
    public List<Transaction> getTransactionByAccountNumber(String accountNumber) {
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
            transObj.setTime(infoUser[1]);
            transObj.setAmount(infoUser[2]);
            transObj.setType(infoUser[3]);
            transObj.setRef(infoUser[4]);
            if (transObj.getAccountNumber().equals(accountNumber)) {
                listObjTransactions.add(transObj);
            }
        });
        return listObjTransactions;
    }
}
