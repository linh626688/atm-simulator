package atmsimulator.DAO.impl;

import atmsimulator.DAO.AccountDAO;
import atmsimulator.model.Account;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static atmsimulator.MainApp.users;

public class AccountDAOImpl implements AccountDAO {
    @Override
    public void importAccount() {
        String fileName = "./input_account.csv";
        List<String> list = new ArrayList<>();

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
            account.setPIN(infoUser[1]);
            account.setBalance(Integer.parseInt(infoUser[2]));
            account.setAccountNumber(infoUser[3]);
            users.add(account);
        });
    }

    @Override
    public Account findUserByAccountNumber(String accountNumber) {
        return users.stream()
                .filter(user -> accountNumber.equals(user.getAccountNumber()))
                .findAny()
                .orElse(null);
    }

    @Override
    public Account findUserByAccountNumberAndPin(String accountNumber, String pin) {
        return users.stream()
                .filter(user -> accountNumber.equals(user.getAccountNumber()) && pin.equals(user.getPIN()))
                .findAny()
                .orElse(null);
    }
}
