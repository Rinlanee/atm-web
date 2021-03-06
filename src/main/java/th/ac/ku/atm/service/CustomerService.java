package th.ac.ku.atm.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import th.ac.ku.atm.classes.Customer;
import th.ac.ku.atm.data.CustomerRepository;


import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CustomerService {
    private CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public void createCustomer(Customer customer) {
        // encrypt pin
        String hashPin = hash(customer.getPin());
        customer.setPin(hashPin);
        repository.save(customer);
    }

    public Customer findCustomer(int id) {
        try {
            return repository.findById(id).get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }
    public List<Customer> getCustomers() {
        return repository.findAll();
    }

    public Customer checkPin(Customer inputCustomer) {
        Customer storeCustomer = findCustomer(inputCustomer.getId());
        if (storeCustomer != null) {
            String hashPin = storeCustomer.getPin();

            if (BCrypt.checkpw(inputCustomer.getPin(), hashPin)) {
                return storeCustomer;
            }
        }
        return null;
    }

    private String hash(String pin) {
        String salt = BCrypt.gensalt(12);
        return BCrypt.hashpw(pin, salt);
    }
}
