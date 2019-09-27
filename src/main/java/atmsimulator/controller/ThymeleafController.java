package atmsimulator.controller;

import atmsimulator.model.Account;
import atmsimulator.model.Transaction;
import atmsimulator.model.dto.FundTransferDTO;
import atmsimulator.model.dto.WithdrawDTO;
import atmsimulator.services.FundTransferServices;
import atmsimulator.services.TransactionServices;
import atmsimulator.services.UserServices;
import atmsimulator.services.WithdrawServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static atmsimulator.Constant.SUCCESS;
import static atmsimulator.utils.Utils.validateAndCalculateWithdrawAmount;

@Controller
public class ThymeleafController {

    @Autowired
    UserServices userServices;

    @Autowired
    TransactionServices transactionServices;

    @Autowired
    FundTransferServices fundTransferServices;

    @Autowired
    WithdrawServices withdrawServices;

    @GetMapping("/")
    public String home() {
        return "/home";
    }

    @GetMapping("/home")
    public String home1() {
        return "/home";
    }

    @GetMapping("/signin")
    public String login(Model model) {
        Account account = new Account();
        model.addAttribute("account", account);
        return "/signin";
    }

    @GetMapping("/user")
    public String user(HttpServletRequest request, Model model) {
        Principal principal = request.getUserPrincipal();
        Optional<Account> account = userServices.getAccountByAccountNumber(principal.getName());
        if (account.isPresent()) {
            model.addAttribute("name", account.get().getName());
            model.addAttribute("balance", account.get().getBalance());
        }
        return "/user";
    }

    @GetMapping("/transaction-logs")
    public String transactionLogs(
            HttpServletRequest request,
            Model model,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        Principal principal = request.getUserPrincipal();

        Page<Transaction> transactions = transactionServices.findTransactionPagination(principal.getName(), new PageRequest(currentPage - 1, pageSize));

        model.addAttribute("transactionsPage", transactions);

        int totalPages = transactions.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "/transaction-logs";
    }

    @GetMapping("/withdraw")
    public String withdraw(Model model) {
        WithdrawDTO withdrawDTO = new WithdrawDTO();
        model.addAttribute("withdrawDTO", withdrawDTO);

        return "/withdraw";
    }

    @PostMapping("/submitWithdraw")
    public String submitWithdraw(HttpServletRequest request, @ModelAttribute("withdrawDTO") WithdrawDTO withdrawDTO) {
        String amount = "";
        String result = "";
        Principal principal = request.getUserPrincipal();
        Optional<Account> account = userServices.getAccountByAccountNumber(principal.getName());

        if (withdrawDTO.getAmount() == null) {
            request.setAttribute("errorMessage", "Error");
            return "withdraw";

        }
        if ("other".equals(withdrawDTO.getAmount())) {
            amount = withdrawDTO.getValue();
        } else {
            amount = withdrawDTO.getAmount();
        }
        String msg = validateAndCalculateWithdrawAmount(1000, amount);
        if (msg == null && account.isPresent()) {
            if (withdrawServices.calculateWithdrawAmount(account.get().getAccountNumber(), account.get().getPin(), Integer.parseInt(amount))) {
                result = "redirect:/user";
            } else {
                request.setAttribute("msg", "Error");
            }
        } else {
            request.setAttribute("errorMessage", msg);
            result = "withdraw";
        }
        return result;
    }

    @GetMapping("/fund-transfer")
    public String fundTransfer(Model model) {
        FundTransferDTO fundTransferDTO = new FundTransferDTO();
        model.addAttribute("fundTransferDTO", fundTransferDTO);
        return "/fund-transfer";
    }

    @PostMapping("/submitTransfer")
    public String submitTransfer(HttpServletRequest request, @ModelAttribute("fundTransferDTO") FundTransferDTO fundTransferDTO) {
        Principal principal = request.getUserPrincipal();
        Optional<Account> account = userServices.getAccountByAccountNumber(principal.getName());
        String view = "";
        if (account.isPresent()) {
            String result =
                    fundTransferServices.submitFundTransaction(
                            account.get().getAccountNumber(),
                            account.get().getPin(),
                            fundTransferDTO.getDestinationAccount(),
                            Integer.parseInt(fundTransferDTO.getAmount()),
                            fundTransferDTO.getRef()
                    );
            if (SUCCESS.equals(result)) {
                view = "redirect:/user";
            } else {
                request.setAttribute("msg", result);
                view = "fund-transfer";
            }
        } else {
            request.setAttribute("msg", "Current Account Not Found");
            view = "fund-transfer";
        }
        return view;

    }

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }
}
