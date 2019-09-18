package atmsimulator.DAO.impl;

import atmsimulator.DAO.TransactionDAO;
import atmsimulator.model.Transaction;
import atmsimulator.utils.Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TransactionDAOImpl implements TransactionDAO {
    @Override
    public void addTransaction(Transaction transaction) {
        Utils.writeTransactionLog(transaction);
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
