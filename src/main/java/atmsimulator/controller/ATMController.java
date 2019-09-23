package atmsimulator.controller;

import atmsimulator.model.Account;
import atmsimulator.model.Transaction;
import atmsimulator.services.FundTransferServices;
import atmsimulator.services.TransactionServices;
import atmsimulator.services.UserServices;
import atmsimulator.services.WithdrawServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static atmsimulator.Constant.OTHER_WITHDRAW_SCREEN;
import static atmsimulator.Constant.REGEX_MATCH_NUMBER;
import static atmsimulator.utils.Utils.dateTimeFormat;

@Controller
public class ATMController {

    @Autowired
    UserServices userServices;

    @Autowired
    TransactionServices transactionServices;

    @Autowired
    FundTransferServices fundTransferServices;

    @Autowired
    WithdrawServices withdrawServices;

    private Account currentAccount = null;
    private String amount = null;

    @RequestMapping("/")
    public ModelAndView showFormLogin(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView view = new ModelAndView("login");
        Account loginBean = new Account();
        view.addObject("loginBean", loginBean);
        return view;

    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response,
                             @ModelAttribute("loginBean") Account loginBean) throws Exception {
        ModelAndView view = null;

        currentAccount = userServices.validateLogin(loginBean.getAccountNumber(), loginBean.getPin());
        if (currentAccount == null) {
            request.setAttribute("msg", "Invalid account or PIN");
            view = new ModelAndView("login");
        } else {
            view = new ModelAndView("redirect:/account-screen");
        }
        return view;
    }

    @RequestMapping(value = "/submitWithdraw", method = RequestMethod.POST)
    public ModelAndView submitWithdraw(HttpServletRequest request, @RequestParam("withdraw-value") String value, @RequestParam("other-value") String otherValue) {
        ModelAndView view = null;
        if ("other".equals(value)) {
            amount = otherValue;
        } else {
            amount = value;
        }
        String msg = validateAndCalculateWithdrawAmount(currentAccount.getBalance(), amount);

        if (msg == null) {
            if(withdrawServices.calculateWithdrawAmount(currentAccount.getAccountNumber(), currentAccount.getPin(), Integer.parseInt(amount))){
                view = new ModelAndView("redirect:/summary");
            }else {
                request.setAttribute("msg", "Error");
            }
        } else {
            request.setAttribute("msg", msg);
            view = new ModelAndView("withdraw");
        }
        return view;
    }


    @RequestMapping("/account-screen")
    public String accountScreen(Model m) {
        currentAccount = userServices.validateLogin(currentAccount.getAccountNumber(), currentAccount.getPin());
        m.addAttribute("account", currentAccount);
        return "account-screen";
    }

    @RequestMapping("/withdraw")
    public String withdraw(Model m) {
//        m.addAttribute("account", currentAccount);
        return "withdraw";
    }

    @RequestMapping("/transaction-log")
    public String latest10TransactionOfAccount(Model m) {
        List<Transaction> transactionList = transactionServices.latest10TransactionByAccount(currentAccount.getAccountNumber());
        m.addAttribute("transactionList", transactionList);
        return "transaction-log";
    }

    @RequestMapping("/summary")
    public String summary(Model m) {
        Account account = userServices.validateLogin(currentAccount.getAccountNumber(), currentAccount.getPin());
        if (account != null) {
            m.addAttribute("accountNumber", account.getAccountNumber());
            m.addAttribute("amount", amount);
            m.addAttribute("balance", account.getBalance());
            m.addAttribute("date", LocalDateTime.now().format(dateTimeFormat));
        }
        return "summary";
    }

    @RequestMapping(value = "/fund-transfer")
    public String edit(Model m) {
        return "fund-transfer";
    }

    @RequestMapping(value = "/submitTransfer", method = RequestMethod.POST)
    public String submitTransfer(
            @RequestParam("accountDestination") String accountDestination,
            @RequestParam("amount") String amount,
            @RequestParam("ref") String ref,
            Model model
    ) {
        System.out.println(accountDestination + " " + amount + " " + ref);

        try {
            if (fundTransferServices.submitFundTransaction(currentAccount.getAccountNumber(), currentAccount.getPin(), accountDestination, Integer.parseInt(amount))) {
                return "redirect:/account-screen";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("message", "error");
        return "redirect:/fund-transfer";
    }

    public String validateAndCalculateWithdrawAmount(int balance, String amount) {
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
