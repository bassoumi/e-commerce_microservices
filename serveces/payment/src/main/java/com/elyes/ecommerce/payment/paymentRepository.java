package com.elyes.ecommerce.payment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface paymentRepository extends JpaRepository<Payment, Integer> {
}
