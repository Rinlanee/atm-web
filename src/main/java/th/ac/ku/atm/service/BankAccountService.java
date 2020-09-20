package th.ac.ku.atm.service;

import org.springframework.stereotype.Service;
import th.ac.ku.atm.classes.BankAccount;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class BankAccountService {

    List<BankAccount> bankAccountList;

    @PostConstruct
    public void postConstruct() {
        this.bankAccountList = new ArrayList<>();
    }

    public void createAccount(BankAccount bankAccount) {
        this.bankAccountList.add(bankAccount);
    }

    public List<BankAccount> getAccount() {
        return new ArrayList<>(this.bankAccountList);
    }
}
