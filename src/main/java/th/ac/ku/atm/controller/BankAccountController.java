package th.ac.ku.atm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.atm.classes.BankAccount;
import th.ac.ku.atm.service.BankAccountService;

@Controller
@RequestMapping("/bankaccount")
public class BankAccountController {

    private BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping
    public String getBankAccountPage(Model model) {
        model.addAttribute("bankaccounts", bankAccountService.getBankAccounts());
        return "bankaccount"; // html filename
    }

    @PostMapping
    public String openAccount(@ModelAttribute BankAccount account, Model model) {
        bankAccountService.openBankAccount(account);
        model.addAttribute("bankaccounts", bankAccountService.getBankAccounts());
        return "redirect:bankaccount"; // redirect: used to prevent calling this method again; after ':' <URI>
    }

    @GetMapping("/edit/{id}")
    public String getEditBankAccountPage(@PathVariable int id, Model model) {
        BankAccount bankAccount = bankAccountService.getBankAccount(id);
        model.addAttribute("bankAccount", bankAccount);
        return "bankaccount-edit";
    }

    @PostMapping("/delete/{id}")
    public String deleteAccount(@PathVariable int id,
                              @ModelAttribute BankAccount bankAccount,
                              Model model) {

        bankAccountService.deleteBankAccount(bankAccount);
        model.addAttribute("bankaccounts",bankAccountService.getBankAccounts());
        return "redirect:/bankaccount";
    }


    @PostMapping("/deposit/{id}")
    public String balanceAfterDeposit(@PathVariable int id, @RequestParam("amount") String amount) {
        BankAccount bankAccount = bankAccountService.getBankAccount(id);
        bankAccount.deposit(Float.parseFloat(amount));
        bankAccountService.editBankAccount(bankAccount);

        return "redirect:/bankaccount";
    }

    @PostMapping("/withdraw/{id}")
    public String balanceAfterWithdraw(@PathVariable int id, @RequestParam("amount") String amount) {
        BankAccount bankAccount = bankAccountService.getBankAccount(id);
        bankAccount.withdraw(Float.parseFloat(amount));
        bankAccountService.editBankAccount(bankAccount);

        return "redirect:/bankaccount";
    }



}
