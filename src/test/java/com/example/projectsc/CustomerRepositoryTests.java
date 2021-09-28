package com.example.projectsc;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class CustomerRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerRepository repo;

    @Test
    public void testCreateCustomer() {
        Customer customer = new Customer();
        customer.setEmail("Ema@gmail.com");
        customer.setPassword("okiygt2");
        customer.setFirstName("Ema");
        customer.setLastName("Jane");

        Customer savedCustomer = repo.save(customer);

        Customer existCustomer = entityManager.find(Customer.class, savedCustomer.getId());

        assertThat(customer.getEmail()).isEqualTo(existCustomer.getEmail());

    }
}
