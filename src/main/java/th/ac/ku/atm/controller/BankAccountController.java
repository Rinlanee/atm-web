package th.ac.ku.atm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import th.ac.ku.atm.classes.BankAccount;
import th.ac.ku.atm.service.BankAccountService;

@Controller
@RequestMapping("/bank_account")
public class BankAccountController {

    private BankAccountService bankAccountService;

    public BankAccountController(BankAccountService customerService) {
        this.bankAccountService = customerService;
    }

//    @GetMapping
//    public String getBankAccountPage(Model model) {
//
//        model.addAttribute("allAccounts", bankAccountService.getAccount());
//
//        return "bankaccount"; // html filename
//    }
//
//    @PostMapping
//    public String createAccount(@ModelAttribute BankAccount account, Model model) {
//        bankAccountService.createAccount(account);
//        model.addAttribute("allAccounts", bankAccountService.getAccount());
//
//        return "redirect:bank_account"; // redirect: used to prevent calling this method again; after ':' <URI>
//    }
}
