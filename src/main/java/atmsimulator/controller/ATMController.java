package atmsimulator.controller;

import atmsimulator.model.Account;
import atmsimulator.model.Transaction;
import atmsimulator.services.FundTransferServices;
import atmsimulator.services.TransactionServices;
import atmsimulator.services.UserServices;
import atmsimulator.services.WithdrawServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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

    @Value("${welcome.message}")
    private String message;

    private Account currentAccount = null;
    private String amount = null;

//    @RequestMapping("/")
//    public String welcome(Map<String, Object> model) {
//        model.put("message", this.message);
//        return "welcome";
//    }

    @RequestMapping("/")
    public String showFormLogin(Model m) {
        m.addAttribute("command", new Account());
        return "login";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("account") Account account) {
        currentAccount = userServices.validateLogin(account.getAccountNumber(), account.getPin());
        if (currentAccount == null) {
            return "login";
        }
        return "redirect:/account-screen";
    }

    @RequestMapping(value = "/submitWithdraw", method = RequestMethod.POST)
    public String submitWithdraw(@RequestParam("withdraw-value") String value, @RequestParam("other-value") String otherValue) {
        if ("other".equals(value)) {
            amount = otherValue;
            withdrawServices.calculateWithdrawAmount(currentAccount.getAccountNumber(), currentAccount.getPin(), Integer.parseInt(otherValue));
        } else {
            amount = value;
            withdrawServices.calculateWithdrawAmount(currentAccount.getAccountNumber(), currentAccount.getPin(), Integer.parseInt(value));
        }
        return "redirect:/summary";
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


//    @RequestMapping(value="/editsave",method = RequestMethod.POST)
//    public String editsave(@ModelAttribute("emp") Emp emp){
//        dao.update(emp);
//        return "redirect:/viewemp";
//    }
//
//    @RequestMapping(value="/deleteemp/{id}",method = RequestMethod.GET)
//    public String delete(@PathVariable int id){
//        dao.delete(id);
//        return "redirect:/viewemp";
//    }

    @ExceptionHandler(value = {Exception.class})
    public ModelAndView handleAllException(Exception ex, HttpServletRequest req) {
        ModelAndView model = new ModelAndView();
        model.addObject("exception", ex);
        return model;
    }
}
