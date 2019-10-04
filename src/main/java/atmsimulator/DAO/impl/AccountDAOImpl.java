package atmsimulator.DAO.impl;

import atmsimulator.DAO.AccountDAO;
import atmsimulator.model.Account;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static atmsimulator.MainApp.users;

public class AccountDAOImpl implements AccountDAO {
    @Override
    public void importAccount() {
        List<String> list = new ArrayList<>();
        Path path = null;
        try {
            Path temp = Files.createTempFile("", "");
            InputStream is = this.getClass().getResourceAsStream("/input_account.csv");
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
            account.setPIN(infoUser[1]);
            account.setBalance(Integer.parseInt(infoUser[2]));
            account.setAccountNumber(infoUser[3]);
            if (users.stream()
                    .filter(item -> item.getAccountNumber().equals(infoUser[3]))
                    .findAny()
                    .orElse(null) == null
            ) {
                users.add(account);
            }
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
