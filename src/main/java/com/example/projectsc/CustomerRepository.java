package com.example.projectsc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT u FROM Customer u WHERE u.email = ?1")
    public Customer findByEmail(String email);
}
