package th.ac.ku.atm.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import th.ac.ku.atm.classes.Customer;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    private List<Customer> customerList;

    @PostConstruct
    public void postConstruct() {
        this.customerList = new ArrayList<>();
    }

    public void createCustomer(Customer customer) {

        // encrypt pin
        String hashPin = hash(customer.getPin());
        customer.setPin(hashPin);

        // add customer to list
        customerList.add(customer);
    }

    public List<Customer> getCustomer() {
        return new ArrayList<>(this.customerList);
    }

    private String hash(String pin) {
        String salt = BCrypt.gensalt(12);

        return BCrypt.hashpw(pin, salt);
    }

    public Customer findCustomer(int id) {
        for (Customer c : customerList) {
            if (c.getId() == id) {
                return c;
            }
        }

        return null;
    }

    public Customer checkPin(Customer inputCustomer) {
        Customer storeCustomer = findCustomer(inputCustomer.getId());
        System.out.println(storeCustomer.getName());

        if (storeCustomer != null) {
            String hashPin = storeCustomer.getPin();

            if (BCrypt.checkpw(inputCustomer.getPin(), hashPin)) {
                return storeCustomer;
            }
        }

        return null;
    }
}
