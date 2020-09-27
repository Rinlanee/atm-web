package th.ac.ku.atm.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import th.ac.ku.atm.classes.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
