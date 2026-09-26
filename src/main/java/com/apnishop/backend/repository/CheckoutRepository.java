package com.apnishop.backend.repository;

import com.apnishop.backend.entity.Checkout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckoutRepository extends JpaRepository<Checkout, String> {
}
